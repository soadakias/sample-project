package com.company.demo_app.model.converters;

import com.company.demo_app.enums.SpecifierType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class SpecifierTypeConverter implements AttributeConverter<SpecifierType, String> {

    @Override
    public String convertToDatabaseColumn(SpecifierType specifierType) {
        if (Objects.isNull(specifierType)) {
            return null;
        }
        return specifierType.getDbValue();
    }

    @Override
    public SpecifierType convertToEntityAttribute(String s) {
        if (Objects.isNull(s)) {
            return null;
        }
        return Stream.of(SpecifierType.values())
                .filter(t -> t.getDbValue().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
