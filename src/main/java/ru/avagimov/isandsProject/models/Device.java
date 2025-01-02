package ru.avagimov.isandsProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamicInsert
@DynamicUpdate
@Table(name = "device")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "device_type", discriminatorType = DiscriminatorType.STRING)
public class Device {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL)
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 0 characters")
    private String name;
    private String serialNumber;
    private String color;
    private String size;
    private BigDecimal price;
    @Column(name = "is_available")
    private boolean available;

    private String category;
    private String processorType;
    private Integer doorCount;
    private String compressorType;
    private String technology;
    private String memory;
    private Integer cameraCount;
    private String dustCollectorVolume;
    private Integer modeNumber;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appliance_id", nullable = false) // referencedColumnName = "id",
    private Appliance appliance;

    public Device() {
    }

    public Device(int id, String name, String serialNumber, String color, String size, BigDecimal price,
                  boolean isAvailable, Appliance appliance) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.color = color;
        this.size = size;
        this.price = price;
        this.available = isAvailable;
        this.appliance = appliance;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }



    public Appliance getAppliance() {
        return appliance;
    }

    public void setAppliance(Appliance appliance) {
        this.appliance = appliance;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProcessorType() {
        return processorType;
    }

    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    public Integer getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(Integer doorCount) {
        this.doorCount = doorCount;
    }

    public String getCompressorType() {
        return compressorType;
    }

    public void setCompressorType(String compressorType) {
        this.compressorType = compressorType;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public Integer getCameraCount() {
        return cameraCount;
    }

    public void setCameraCount(Integer cameraCount) {
        this.cameraCount = cameraCount;
    }

    public String getDustCollectorVolume() {
        return dustCollectorVolume;
    }

    public void setDustCollectorVolume(String dustCollectorVolume) {
        this.dustCollectorVolume = dustCollectorVolume;
    }

    public Integer getModeNumber() {
        return modeNumber;
    }

    public void setModeNumber(Integer modeNumber) {
        this.modeNumber = modeNumber;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", category='" + category + '\'' +
                ", processorType='" + processorType + '\'' +
                ", doorCount=" + doorCount +
                ", compressorType='" + compressorType + '\'' +
                ", technology='" + technology + '\'' +
                ", memory='" + memory + '\'' +
                ", cameraCount=" + cameraCount +
                ", dustCollectorVolume='" + dustCollectorVolume + '\'' +
                ", modeNumber=" + modeNumber +
                ", appliance=" + appliance +
                '}';
    }
}
