package ru.avagimov.isandsProject.util;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.avagimov.isandsProject.types.ApplianceType;

@Component
public class ApplianceTypeConverter implements Converter<String, ApplianceType> {
    @Override
    public ApplianceType convert(String source) {
        return ApplianceType.valueOf(source.toUpperCase());
    }
}
