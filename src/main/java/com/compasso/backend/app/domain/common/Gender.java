package com.compasso.backend.app.domain.common;

public enum Gender {

    MALE("Male","M"),
    FEMALE("Female", "F"),
    OTHER("Other", "O");

    public String description;

    public String initial;

    Gender(String description, String initial) {
        this.description = description;
        this.initial = initial;
    }

}
