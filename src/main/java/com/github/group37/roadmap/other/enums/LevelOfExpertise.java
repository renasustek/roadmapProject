package com.github.group37.roadmap.other.enums;

public enum LevelOfExpertise {
    NOVICE,
    INTERMEDIATE,
    EXPERT;

    public String getValue() {
        return this.name();
    }

    public static LevelOfExpertise fromValue(String value) {
        for (LevelOfExpertise expertise : values()) {
            if (expertise.name().equalsIgnoreCase(value)) {
                return expertise;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}