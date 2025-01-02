package ru.avagimov.isandsProject.factory;

import ru.avagimov.isandsProject.models.*;
import ru.avagimov.isandsProject.types.ApplianceType;

public class DeviceFactory {
    public static Device createDevice(ApplianceType type, Device device) {
        System.out.println("Input device: " + device);
        switch (type) {
            case COMPUTER -> {
                Computer computer = new Computer(
                        device.getId(),
                        device.getName(),
                        device.getSerialNumber(),
                        device.getColor(),
                        device.getSize(),
                        device.getPrice(),
                        device.isAvailable(),
                        device.getAppliance(),
                        device.getCategory(),
                        device.getProcessorType()
                );

                System.out.println("Created Computer: " + computer);
                return computer;
            }
            case REFRIGERATOR -> {
                Refrigerator refrigerator = new Refrigerator(
                        device.getId(),
                        device.getName(),
                        device.getSerialNumber(),
                        device.getColor(),
                        device.getSize(),
                        device.getPrice(),
                        device.isAvailable(),
                        device.getAppliance(),
                        device.getDoorCount(),
                        device.getCompressorType()
                );
                System.out.println("Created Refrigerator: " + refrigerator);
                return refrigerator;
            }
            case SMARTPHONE -> {
                Smartphone smartphone = new Smartphone(device.getId(),
                        device.getName(),
                        device.getSerialNumber(),
                        device.getColor(),
                        device.getSize(),
                        device.getPrice(),
                        device.isAvailable(),
                        device.getAppliance(),
                        device.getMemory(),
                        device.getCameraCount()
                );
                System.out.println("Created Smartphone: " + smartphone);
                return smartphone;
            }
            case TV -> {
                if (device.getCategory() == null || device.getTechnology() == null) {
                    throw new IllegalArgumentException("Category and technology cannot be null for TV");
                }
                Tv tv = new Tv(
                        device.getId(),
                        device.getName(),
                        device.getSerialNumber(),
                        device.getColor(),
                        device.getSize(),
                        device.getPrice(),
                        device.isAvailable(),
                        device.getAppliance(),
                        device.getCategory(),
                        device.getTechnology()
                );
                System.out.println("Created Tv: " + tv);
                return tv;
            }
            case VACUUM_CLEANER -> {
                VacuumCleaner vacuumCleaner = new VacuumCleaner(
                        device.getId(),
                        device.getName(),
                        device.getSerialNumber(),
                        device.getColor(),
                        device.getSize(),
                        device.getPrice(),
                        device.isAvailable(),
                        device.getAppliance(),
                        device.getDustCollectorVolume(),
                        device.getModeNumber()
                );
                System.out.println("Created VacuumCleaner: " + vacuumCleaner);
                return vacuumCleaner;
            }
            default -> {
                return device;
            }

        }
    }
}
