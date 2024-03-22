package com.github.group37.roadmap.other.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LevelOfExpertiseConverter implements AttributeConverter<LevelOfExpertise, String> {

    @Override
    public String convertToDatabaseColumn(LevelOfExpertise attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public LevelOfExpertise convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return LevelOfExpertise.fromValue(dbData);
    }
}