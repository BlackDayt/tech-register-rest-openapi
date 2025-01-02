package ru.avagimov.isandsProject.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("COMPUTER")
public class Computer extends Device {

    public Computer(int id, String name, String serialNumber, String color, String size, BigDecimal price,
                    boolean isAvailable, Appliance appliance, String category, String processorType) {
        super(id, name, serialNumber, color, size, price, isAvailable, appliance);
        this.setCategory(category);
        this.setProcessorType(processorType);

        System.out.println("Computer constructor created: " + this);
    }

    public Computer() {

    }

}
