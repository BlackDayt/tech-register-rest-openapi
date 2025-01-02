package ru.avagimov.isandsProject.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import ru.avagimov.isandsProject.models.Device;
import ru.avagimov.isandsProject.types.ApplianceType;

import java.math.BigDecimal;
import java.util.Map;

public class DeviceSpecification {
    public static Specification<Device> filterByName(String name) {
        return (root, query, builder) -> {
            if (name == null || name.isEmpty()) {
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Device> filterByColor(String color) {
        return (root, query, builder) -> {
            if (color == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("color"), color);
        };
    }

    public static Specification<Device> filterByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, builder) -> {
            if (minPrice == null && maxPrice == null) {
                return builder.conjunction();
            }
            if (minPrice != null && maxPrice != null) {
                return builder.between(root.get("price"), minPrice, maxPrice);
            }
            if (minPrice != null) {
                return builder.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            return builder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    public static Specification<Device> filterByModelAttributes(ApplianceType applianceType, Map<String, String> modelAttributes) {
        return (root, query, builder) -> {
            if (applianceType == null || modelAttributes == null || modelAttributes.isEmpty()){
                return builder.conjunction();
            }

            Predicate predicate = builder.equal(root.get("appliance").get("applianceType"), applianceType);

            if (applianceType.equals(ApplianceType.COMPUTER)) {
                if (modelAttributes.containsKey("category")) {
                    predicate = builder.and(predicate, builder.equal(root.get("category"), modelAttributes.get("category")));
                }
                if (modelAttributes.containsKey("processorType")) {
                    predicate = builder.and(predicate, builder.equal(root.get("processorType"), modelAttributes.get("processorType")));
                }
            }

            if (applianceType.equals(ApplianceType.REFRIGERATOR)) {
                if (modelAttributes.containsKey("doorCount")) {
                    predicate = builder.and(predicate, builder.equal(root.get("doorCount"), modelAttributes.get("doorCount")));
                }
                if (modelAttributes.containsKey("compressorType")) {
                    predicate = builder.and(predicate, builder.equal(root.get("compressorType"), modelAttributes.get("compressorType")));
                }
            }

            if (applianceType.equals(ApplianceType.SMARTPHONE)) {
                if (modelAttributes.containsKey("memory")) {
                    predicate = builder.and(predicate, builder.equal(root.get("memory"), modelAttributes.get("memory")));
                }
                if (modelAttributes.containsKey("cameraCount")) {
                    predicate = builder.and(predicate, builder.equal(root.get("cameraCount"), modelAttributes.get("cameraCount")));
                }
            }

            if (applianceType.equals(ApplianceType.TV)) {
                if (modelAttributes.containsKey("category")) {
                    predicate = builder.and(predicate, builder.equal(root.get("category"), modelAttributes.get("category")));
                }
                if (modelAttributes.containsKey("technology")) {
                    predicate = builder.and(predicate, builder.equal(root.get("technology"), modelAttributes.get("technology")));
                }
            }

            if (applianceType.equals(ApplianceType.VACUUM_CLEANER)) {
                if (modelAttributes.containsKey("dustCollectorVolume")) {
                    predicate = builder.and(predicate, builder.equal(root.get("dustCollectorVolume"), modelAttributes.get("dustCollectorVolume")));
                }
                if (modelAttributes.containsKey("modeNumber")) {
                    predicate = builder.and(predicate, builder.equal(root.get("modeNumber"), modelAttributes.get("modeNumber")));
                }
            }

            return predicate;
        };
    }
}
