package ru.avagimov.isandsProject.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("REFRIGERATOR")
public class Refrigerator extends Device {

    public Refrigerator(int id, String name, String serialNumber, String color, String size, BigDecimal price,
                        boolean isAvailable, Appliance appliance, int doorCount, String compressorType) {
        super(id, name, serialNumber, color, size, price, isAvailable, appliance);
        this.setDoorCount(doorCount);
        this.setCompressorType(compressorType);
    }

    public Refrigerator() {

    }

}
