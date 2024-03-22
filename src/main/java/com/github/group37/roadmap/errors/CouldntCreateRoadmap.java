package com.github.group37.roadmap.errors;

public class CouldntCreateRoadmap extends RuntimeException {

    public CouldntCreateRoadmap() {
        super("could not generate roadmap");
    }
}
