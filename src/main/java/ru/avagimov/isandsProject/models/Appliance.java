package ru.avagimov.isandsProject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.http.converter.json.GsonBuilderUtils;
import ru.avagimov.isandsProject.types.ApplianceType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Schema(description = "Представление техники")
public class Appliance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String country;
    private String manufacturer;
    private boolean onlineOrderAvailable;
    private boolean installmentAvailable;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Тип устройства")
    private ApplianceType applianceType;

    @OneToMany(mappedBy = "appliance", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Device> allModels = new ArrayList<>();

    @Transient
    private List<Device> availableModels;
    public Appliance() {
    }

    public Appliance(String name, String country, String manufacturer, boolean onlineOrderAvailable,
                     boolean installmentAvailable, ApplianceType applianceType) {
        this.name = name;
        this.country = country;
        this.manufacturer = manufacturer;
        this.onlineOrderAvailable = onlineOrderAvailable;
        this.installmentAvailable = installmentAvailable;
        this.applianceType = applianceType;

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

    public void setName(String applianceType) {
        this.name = applianceType;
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

    public ApplianceType getApplianceType() {
        return applianceType;
    }

    public void setApplianceType(ApplianceType applianceType) {
        this.applianceType = applianceType;
    }

    public List<Device> getAllModels() {
        return allModels;
    }

    public void setAllModels(List<Device> devices) {
        this.allModels = devices;
    }

    public void addModel(Device device) {
        allModels.add(device);
    }

    public List<Device> getAvailableModels() {
        return availableModels;
    }

    public void setAvailableModels(List<Device> availableModels) {
        this.availableModels = availableModels;
    }

    public void clearModelList() {
        allModels.clear();
    }
    @PostLoad
    private void filterAvailableModels() {
        this.availableModels = allModels.stream().filter(Device::isAvailable).toList();
    }
}
