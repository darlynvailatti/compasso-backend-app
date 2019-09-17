package com.compasso.backend.app.rest.dto;

import com.compasso.backend.app.rest.pattern.AbstractRestDTO;

public class CityDTO extends AbstractRestDTO {

    private Long id;

    private String name;

    private FederativeUnitDTO federativeUnit;

    public CityDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FederativeUnitDTO getFederativeUnit() {
        return federativeUnit;
    }

    public void setFederativeUnit(FederativeUnitDTO federativeUnit) {
        this.federativeUnit = federativeUnit;
    }
}
