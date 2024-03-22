package com.github.group37.roadmap.errors;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super("could not find user:  " + id + "  :(");
    }
}
