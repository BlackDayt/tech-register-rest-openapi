package ru.avagimov.isandsProject.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("VACUUM_CLEANER")
public class VacuumCleaner extends Device {

    public VacuumCleaner(int id, String name, String serialNumber, String color, String size, BigDecimal price,
                         boolean isAvailable, Appliance appliance, String dustCollectorVolume, int modeNumber) {
        super(id, name, serialNumber, color, size, price, isAvailable, appliance);
        this.setDustCollectorVolume(dustCollectorVolume);
        this.setModeNumber(modeNumber);
    }

    public VacuumCleaner() {

    }

}
