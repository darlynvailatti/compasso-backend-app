package com.compasso.backend.app.rest.dto;

import com.compasso.backend.app.rest.pattern.AbstractRestDTO;

public enum GenderDTOEnum {
    MALE("M",0),
    FEMALE("F", 1),
    UNDEFINED("U", 2);

    public String initial;

    public Integer code;

    GenderDTOEnum(String initial, Integer code) {
        this.initial = initial;
        this.code = code;
    }


}
