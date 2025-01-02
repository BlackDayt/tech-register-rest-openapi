package ru.avagimov.isandsProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.avagimov.isandsProject.models.Appliance;
import ru.avagimov.isandsProject.repositories.AppliancesRepository;
import ru.avagimov.isandsProject.util.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AppliancesService {
    private final AppliancesRepository appliancesRepository;

    @Autowired
    public AppliancesService(AppliancesRepository appliancesRepository) {
        this.appliancesRepository = appliancesRepository;
    }

    @Transactional
    public Appliance create(Appliance appliance) {
        return appliancesRepository.save(appliance);
    }

    @Transactional
    public Appliance update(int id, Appliance appliance) {
        Appliance updatedAppliance = getApplianceById(id);
        updatedAppliance.setName(appliance.getName());
        updatedAppliance.setManufacturer(appliance.getManufacturer());
        updatedAppliance.setCountry(appliance.getCountry());
        updatedAppliance.setOnlineOrderAvailable(appliance.isOnlineOrderAvailable());
        updatedAppliance.setInstallmentAvailable(appliance.isInstallmentAvailable());
        return appliancesRepository.save(updatedAppliance);
    }

    @Transactional
    public void delete(int id) {
        if (!appliancesRepository.existsById(id))
            throw new ResourceNotFoundException("Appliance not found with id: " + id);
        appliancesRepository.deleteById(id);
    }

    public List<Appliance> findAll() {
        return appliancesRepository.findAll();
    }

    public Appliance findById(int id) {
        Optional<Appliance> appliance = appliancesRepository.findById(id);
        return appliance.orElse(null);
    }

    public Appliance getApplianceById(int id) {
        return appliancesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appliance not found with id: " + id));
    }
}
