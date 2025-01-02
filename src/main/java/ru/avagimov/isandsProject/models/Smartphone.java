package ru.avagimov.isandsProject.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("SMARTPHONE")
public class Smartphone extends Device {

    public Smartphone(int id, String name, String serialNumber, String color, String size, BigDecimal price,
                      boolean isAvailable, Appliance appliance, String memory, int cameraCount) {
        super(id, name, serialNumber, color, size, price, isAvailable, appliance);
        this.setMemory(memory);
        this.setCameraCount(cameraCount);
    }

    public Smartphone() {

    }

}
