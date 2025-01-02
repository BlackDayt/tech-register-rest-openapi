package ru.avagimov.isandsProject.types;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.avagimov.isandsProject.models.*;


public enum ApplianceType {
    @Schema(description = "Телевизор")
    TV(Tv.class),
    @Schema(description = "Смартфон")
    SMARTPHONE(Smartphone.class),
    @Schema(description = "Компьютер")
    COMPUTER(Computer.class),
    @Schema(description = "Холодильник")
    REFRIGERATOR(Refrigerator.class),
    @Schema(description = "Пылесос")
    VACUUM_CLEANER(VacuumCleaner.class);

    private final Class<?> applianceClass;

    ApplianceType(Class<?> applianceClass) {
        this.applianceClass = applianceClass;
    }

    public Class<?> getApplianceClass() {
        return applianceClass;
    }
}