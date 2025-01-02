package ru.avagimov.isandsProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import ru.avagimov.isandsProject.models.Appliance;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceDTO {
    @NotNull
    private String name;
    @NotNull
    private String serialNumber;
    private String color;
    private String size;
    @NotNull
    private BigDecimal price;
    @NotNull
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
}
