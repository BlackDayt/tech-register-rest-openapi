package ru.avagimov.isandsProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.avagimov.isandsProject.specification.DeviceSpecification;
import ru.avagimov.isandsProject.factory.DeviceFactory;
import ru.avagimov.isandsProject.models.Appliance;
import ru.avagimov.isandsProject.models.Device;
import ru.avagimov.isandsProject.repositories.ModelsRepository;
import ru.avagimov.isandsProject.types.ApplianceType;
import ru.avagimov.isandsProject.util.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Primary
@Transactional(readOnly = true)
public class ModelService {
    private final ModelsRepository modelsRepository;

    @Autowired
    public ModelService(ModelsRepository modelsRepository) {
        this.modelsRepository = modelsRepository;
    }

    @Transactional
    public Device save(Device device, Appliance appliance) {
        Device createdDevice = DeviceFactory.createDevice(appliance.getApplianceType(), device);

        System.out.println("Device before save: " + createdDevice);

        createdDevice.setAppliance(appliance);
        appliance.addModel(createdDevice);
        return  modelsRepository.save(createdDevice);
    }

    @Transactional
    public List<Device> save(List<Device> devices, Appliance appliance) {
        List<Device> createdDevices = devices.stream().map(device -> DeviceFactory.
                createDevice(appliance.getApplianceType(), device)).toList();


        for (Device device :
                createdDevices) {
            System.out.println("Device before save: " + device);
            device.setAppliance(appliance);
            appliance.addModel(device);
        }

        return  createdDevices.stream().map(modelsRepository::save).toList();
    }

    @Transactional
    public Device update(int id, Device device ) {
        Device updatedDevice = getDeviceById(id);
        updatedDevice.setName(device.getName());
        updatedDevice.setSerialNumber(device.getSerialNumber());
        updatedDevice.setColor(device.getColor());
        updatedDevice.setSize(device.getSize());
        updatedDevice.setPrice(device.getPrice());
        updatedDevice.setAvailable(device.isAvailable());


        return modelsRepository.save(updatedDevice);
    }

    @Transactional
    public void delete(int id) {
        if (!modelsRepository.existsById(id))
            throw new ResourceNotFoundException("Device not found with id: " + id);
        modelsRepository.deleteById(id);
    }

    /////FILTERS AND SEARCH
    public List<Device> searchDevices(@Nullable String searchQuery, @Nullable String parameter) {
        String field = Optional.ofNullable(parameter).orElse("name");

        System.out.println("Search parameters - Name: " + searchQuery + ", Serial Number: " + field);
        return modelsRepository.searchByField(searchQuery, field);
    }

    public List<Device> filterDevice(@Nullable String name, @Nullable ApplianceType deviceType, @Nullable String color,
                                     @Nullable BigDecimal minPrice, @Nullable BigDecimal maxPrice,
                                     @Nullable Map<String, String> modelAttributes) {

        if (minPrice == null) minPrice = BigDecimal.ZERO;
        if (maxPrice == null) maxPrice = BigDecimal.valueOf(Double.MAX_VALUE);

        Specification<Device> spec = Specification.where(DeviceSpecification.filterByName(name))
//                .and(DeviceSpecification.filterByDeviceType(deviceType))
                .and(DeviceSpecification.filterByColor(color))
                .and(DeviceSpecification.filterByPriceRange(minPrice, maxPrice))
                .and(DeviceSpecification.filterByModelAttributes(deviceType, modelAttributes));
        return modelsRepository.findAll(spec);
    }

    ///SORT

    public List<Device> getSortedDevices(@Nullable String sortBy, @Nullable String direction) {
        String field = Optional.ofNullable(sortBy).orElse("id");
        boolean isAscending = Optional.ofNullable(direction)
                .map(dir -> dir.equalsIgnoreCase("asc"))
                .orElse(true);

        Sort.Order order = isAscending ? Sort.Order.asc(field) : Sort.Order.desc(field);
        Sort sort = Sort.by(order);
        return modelsRepository.findAll(sort);
    }

    /////////////////////////////

    public List<Device> findAll() {
        return modelsRepository.findAll();
    }

    public Device findById(int id) {
        Optional<Device> device = modelsRepository.findById(id);
        return device.orElse(null);
    }

    public Device getDeviceById(int id) {
        return modelsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + id));
    }
}
