package ru.avagimov.isandsProject.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("TV")
public class Tv extends Device {

    public Tv(int id, String name, String serialNumber, String color, String size, BigDecimal price,
              boolean isAvailable, Appliance appliance, String category, String technology) {
        super(id, name, serialNumber, color, size, price, isAvailable, appliance);
        this.setCategory(category);
        this.setTechnology(technology);
    }

    public Tv() {

    }

}
