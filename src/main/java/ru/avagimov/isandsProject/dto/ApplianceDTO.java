package ru.avagimov.isandsProject.dto;

import ru.avagimov.isandsProject.models.Device;

import java.util.ArrayList;
import java.util.List;

public class ApplianceDTO {

    private String name;
    private String country;
    private String manufacturer;
    private boolean onlineOrderAvailable;
    private boolean installmentAvailable;
    private String applianceType;

    private List<Device> availableModels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplianceType() {
        return applianceType;
    }

    public void setApplianceType(String applianceType) {
        this.applianceType = applianceType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isOnlineOrderAvailable() {
        return onlineOrderAvailable;
    }

    public void setOnlineOrderAvailable(boolean onlineOrderAvailable) {
        this.onlineOrderAvailable = onlineOrderAvailable;
    }

    public boolean isInstallmentAvailable() {
        return installmentAvailable;
    }

    public void setInstallmentAvailable(boolean installmentAvailable) {
        this.installmentAvailable = installmentAvailable;
    }

    public List<Device> getAvailableModels() {
        return availableModels;
    }

    public void setAvailableModels(List<Device> availableModels) {
        this.availableModels = availableModels;
    }
}
