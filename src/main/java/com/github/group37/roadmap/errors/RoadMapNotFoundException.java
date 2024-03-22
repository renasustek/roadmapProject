package com.github.group37.roadmap.errors;

public class RoadMapNotFoundException extends RuntimeException {

    public RoadMapNotFoundException() {
        super("roadmap couldnt be found");
    }
}
