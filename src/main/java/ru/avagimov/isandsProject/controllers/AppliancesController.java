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

import java.util.List;

@RestController
@RequestMapping("/appliance")
public class AppliancesController {

    private final AppliancesService appliancesService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppliancesController(AppliancesService appliancesService, ModelMapper modelMapper) {
        this.appliancesService = appliancesService;
        this.modelMapper = modelMapper;
    }

    ///////////////////////
    // Appliance
    ///////////////////////

    ////GET
    @GetMapping()
    public ResponseEntity<List<ApplianceDTO>> index() {
        List<ApplianceDTO> appliances = appliancesService.findAll().stream().map(this::convertToDTO).toList();
        if (appliances.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.status(HttpStatus.OK).body(appliances); // 200 OK
    }

    @GetMapping("/{applianceId}")
    public ResponseEntity<ApplianceDTO> getAppliance(@PathVariable int applianceId) {
        return ResponseEntity.ok(convertToDTO(appliancesService.findById(applianceId)));
    }




    ////POST
    @PostMapping("/add")
    public ResponseEntity<ApplianceDTO> createAppliance(@RequestBody @Valid ApplianceDTO applianceDTO) {
        Appliance appliance = convertToAppliance(applianceDTO); // Convert DTO в Appliance

        Appliance createdAppliance = appliancesService.create(appliance);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDTO(createdAppliance));
    }

    @PostMapping("/addList")
    public ResponseEntity<List<ApplianceDTO>> createApplianceList(@RequestBody @Valid List<ApplianceDTO> applianceDTOs) {
        List<Appliance> appliances = applianceDTOs.stream().map(this::convertToAppliance).toList();

        for (Appliance appliance :
                appliances) {
            appliancesService.create(appliance);
        }

        List<ApplianceDTO> createdAppliances = appliances.stream().map(this::convertToDTO).toList();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdAppliances);
    }


    ////PATCH
    @PatchMapping("/{applianceId}")
    public ResponseEntity<ApplianceDTO> updateAppliance(@PathVariable int applianceId, @RequestBody @Valid ApplianceDTO applianceDTO) {
        Appliance appliance = convertToAppliance(applianceDTO); // Convert DTO в Appliance
        Appliance updatedAppliance = appliancesService.update(applianceId, appliance);
        return ResponseEntity.ok(convertToDTO(updatedAppliance));
    }


    ////DELETE
    @DeleteMapping("/{applianceId}")
    public ResponseEntity<HttpStatus> deleteAppliance(@PathVariable int applianceId) {
        appliancesService.delete(applianceId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{applianceId}/models")
    public ResponseEntity<HttpStatus> deleteAllModels(@PathVariable int applianceId) {
        appliancesService.findById(applianceId).clearModelList();
        appliancesService.findById(applianceId).getAllModels().clear();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    ///////////////////
    // Filter
    ///////////////////



    public ApplianceDTO convertToDTO(Appliance appliance) {
        return modelMapper.map(appliance, ApplianceDTO.class);
    }

    public DeviceDTO convertToDTO(Device device) {
        return modelMapper.map(device, DeviceDTO.class);
    }

    public Appliance convertToAppliance(ApplianceDTO applianceDTO) {
        return modelMapper.map(applianceDTO, Appliance.class);
    }

    public Device convertToDevice(DeviceDTO deviceDTO) {
        return modelMapper.map(deviceDTO, Device.class);
    }
}
