package ru.avagimov.isandsProject.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.avagimov.isandsProject.dto.ApplianceDTO;
import ru.avagimov.isandsProject.dto.DeviceDTO;
import ru.avagimov.isandsProject.models.Appliance;
import ru.avagimov.isandsProject.models.Device;
import ru.avagimov.isandsProject.services.AppliancesService;
import ru.avagimov.isandsProject.services.ModelService;
import ru.avagimov.isandsProject.types.ApplianceType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    private final AppliancesService appliancesService;
    private final ModelService service;
    private final ModelMapper modelMapper;

    @Autowired
    public CatalogController(AppliancesService appliancesService, ModelService service, ModelMapper modelMapper) {
        this.appliancesService = appliancesService;
        this.service = service;
        this.modelMapper = modelMapper;
    }





    ///////////////////////
    // Device from models
    ///////////////////////

    ////GET
    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> index() {
        List<DeviceDTO> models = service.findAll().stream().map(this::convertToDTO).toList();



        if (models.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.status(HttpStatus.OK).body(models); // 200 OK
    }


    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceDTO> getDevice(@PathVariable int deviceId) {
        DeviceDTO deviceDTO = convertToDTO(service.getDeviceById(deviceId));


        return ResponseEntity.status(HttpStatus.OK).body(deviceDTO); // 200 OK
    }

    ////Post
    @PostMapping("/addModel")
    public ResponseEntity<DeviceDTO> createDevice(@RequestBody @Valid DeviceDTO deviceDTO, @RequestParam int applianceId) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(convertToDTO(service.save(convertToDevice(deviceDTO), appliancesService.findById(applianceId))));
    }


    ////PATCH
    @PatchMapping("/{deviceId}")
    public ResponseEntity<DeviceDTO> updateDevice(@PathVariable int deviceId,
                                                            @RequestBody @Valid DeviceDTO deviceDTO) {
        Device updatedDevice = service.update(
                deviceId,
                convertToDevice(deviceDTO)
        );
        return ResponseEntity.status(HttpStatus.OK).body(convertToDTO(updatedDevice)); // 200 OK
    }


    ////DELETE
    @DeleteMapping("/{deviceId}")
    public ResponseEntity<HttpStatus> deleteDevice(@PathVariable int deviceId) {
        service.delete(deviceId);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    //////////////////////
    // SEARCH AND FILTERS
    //////////////////////
    @GetMapping("/search")
    public ResponseEntity<List<DeviceDTO>> searchDevices(
            @RequestParam(required = false) String searchQuery,
            @RequestParam(required = false) String field) {


        List<Device> devices = service.searchDevices(searchQuery, field);
        if (devices.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(devices.stream().map(this::convertToDTO).toList());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<DeviceDTO>> filterDevices(
            @RequestParam(required = false) String name,                 // По наименованию
            @RequestParam(required = false) ApplianceType deviceType,    // По виду техники
            @RequestParam(required = false) String color,                // По цвету
            @RequestParam(required = false) BigDecimal minPrice,         // Минимальная цена
            @RequestParam(required = false) BigDecimal maxPrice,         // Максимальная цена
            @RequestParam(required = false) Map<String, String> modelAttributes  // Атрибуты модели (зависит от вида техники)
    ) {
        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }

        List<Device> filteredDevices = service.filterDevice(name, deviceType, color, minPrice, maxPrice, modelAttributes);
        if (filteredDevices.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(filteredDevices.stream().map(this::convertToDTO).toList());
    }


    // Сортировка по имени или цене
    @GetMapping("/sort")
    public List<DeviceDTO> getSortedDevices(
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        return service.getSortedDevices(sortBy, direction).stream().map(this::convertToDTO).toList();
    }



    ///////////////////////
    // Mappers
    ///////////////////////


    public DeviceDTO convertToDTO(Device device) {
        return modelMapper.map(device, DeviceDTO.class);
    }

    public Device convertToDevice(DeviceDTO deviceDTO) {
        return modelMapper.map(deviceDTO, Device.class);
    }

    public ApplianceDTO convertToDTO(Appliance appliance) {
        return modelMapper.map(appliance, ApplianceDTO.class);
    }

    public Appliance convertToAppliance(ApplianceDTO applianceDTO) {
        return modelMapper.map(applianceDTO, Appliance.class);
    }
}
