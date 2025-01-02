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
import ru.avagimov.isandsProject.services.ModelService;
import ru.avagimov.isandsProject.services.AppliancesService;

import java.util.List;

@RestController
@RequestMapping("/appliance/{applianceId}")
public class ModelController {
    private final AppliancesService appliancesService;
    private final ModelService service;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelController(AppliancesService appliancesService, ModelService service, ModelMapper modelMapper) {
        this.appliancesService = appliancesService;
        this.service = service;
        this.modelMapper = modelMapper;
    }





    ///////////////////////
    // Device from models
    ///////////////////////

    ////GET
    @GetMapping("/models")
    public ResponseEntity<List<DeviceDTO>> getModels(@PathVariable int applianceId) {
        Appliance appliance = appliancesService.getApplianceById(applianceId);
        List<Device> models = appliance.getAvailableModels();

        if (models.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.status(HttpStatus.OK).body(models.stream().map(this::convertToDTO).toList()); // 200 OK
    }


    @GetMapping("/models/{deviceListId}")
    public ResponseEntity<DeviceDTO> getDeviceFromModels(@PathVariable int applianceId, @PathVariable int deviceListId) {
        Appliance appliance = appliancesService.getApplianceById(applianceId);
        List<Device> models = appliance.getAvailableModels();

        if (models.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.status(HttpStatus.OK).body(convertToDTO(models.get(deviceListId))); // 200 OK
    }

    @GetMapping("/allModels")
    public ResponseEntity<List<DeviceDTO>> getAllModels(@PathVariable int applianceId) {
        Appliance appliance = appliancesService.getApplianceById(applianceId);
        List<Device> models = appliance.getAllModels();

        if (models.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.status(HttpStatus.OK).body(models.stream().map(this::convertToDTO).toList()); // 200 OK
    }

    @GetMapping("/allModels/{deviceListId}")
    public ResponseEntity<DeviceDTO> getDeviceFromAllModels(@PathVariable int applianceId, @PathVariable int deviceListId) {
        Appliance appliance = appliancesService.getApplianceById(applianceId);
        List<Device> allModels = appliance.getAllModels();

        if (allModels.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.status(HttpStatus.OK).body(convertToDTO(allModels.get(deviceListId))); // 200 OK
    }


    @PostMapping("/addModel")
    public ResponseEntity<DeviceDTO> createDeviceFromModels(@RequestBody @Valid DeviceDTO deviceDTO,
                                                            @PathVariable int applianceId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(convertToDTO(service.save(convertToDevice(deviceDTO), appliancesService.findById(applianceId))));
    }



    @PostMapping("/addModelList")
    public ResponseEntity<List<DeviceDTO>> createDeviceListFromModels(@RequestBody @Valid List<DeviceDTO> deviceDTOs,
                                                                      @PathVariable int applianceId
    ) {
        Appliance appliance = appliancesService.findById(applianceId);
        List<Device> devices = service.save(deviceDTOs.stream().map(this::convertToDevice).toList(), appliance);
        return ResponseEntity.status(HttpStatus.CREATED).body(devices.stream().map(this::convertToDTO).toList());
    }


    ////PATCH
    @PatchMapping("/models/{deviceListId}")
    public ResponseEntity<DeviceDTO> updateDeviceFromModels(@PathVariable int applianceId,
                                                            @PathVariable int deviceListId,
                                                            @RequestBody @Valid DeviceDTO deviceDTO
    ) {
        Appliance appliance = appliancesService.getApplianceById(applianceId);
        List<Device> models = appliance.getAvailableModels();

        if (models.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        Device updatedDevice = service.update(
                appliance.getAvailableModels().get(deviceListId).getId(),
                convertToDevice(deviceDTO)
        );

        return ResponseEntity.status(HttpStatus.OK).body(convertToDTO(updatedDevice)); // 200 OK
    }

    @PatchMapping("/allModels/{deviceListId}")
    public ResponseEntity<DeviceDTO> updateDeviceFromAllModels(@PathVariable int applianceId,
                                                               @PathVariable int deviceListId,
                                                               @RequestBody @Valid DeviceDTO deviceDTO
    ) {
        Appliance appliance = appliancesService.getApplianceById(applianceId);
        List<Device> allModels = appliance.getAllModels();

        if (allModels.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        Device updatedDevice = service.update(
                appliance.getAllModels().get(deviceListId).getId(),
                convertToDevice(deviceDTO)
        );
        return ResponseEntity.status(HttpStatus.OK).body(convertToDTO(updatedDevice)); // 200 OK
    }


    ////DELETE
    @DeleteMapping("/models/{deviceListId}")
    public ResponseEntity<HttpStatus> deleteDeviceFromModels(@PathVariable int applianceId,
                                                            @PathVariable int deviceListId) {
        Appliance appliance = appliancesService.getApplianceById(applianceId);
        List<Device> models = appliance.getAvailableModels();

        if (models.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        service.delete(
                appliance.getAvailableModels().get(deviceListId).getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/allModels/{deviceListId}")
    public ResponseEntity<HttpStatus> deleteDeviceFromAllModels(@PathVariable int applianceId,
                                                               @PathVariable int deviceListId) {
        Appliance appliance = appliancesService.getApplianceById(applianceId);
        List<Device> allModels = appliance.getAllModels();

        if (allModels.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        service.delete(appliance.getAllModels().get(deviceListId).getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/allModels")
    public ResponseEntity<HttpStatus> deleteAllModels(@PathVariable int applianceId) {
        Appliance appliance = appliancesService.getApplianceById(applianceId);
        List<Device> allModels = appliance.getAllModels();

        if (allModels.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        for (Device device :
                allModels) {
            service.delete(device.getId());
        }
        allModels.clear();
        return ResponseEntity.ok(HttpStatus.OK);
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
