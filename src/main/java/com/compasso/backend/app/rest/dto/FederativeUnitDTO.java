package com.compasso.backend.app.rest.dto;

import com.compasso.backend.app.rest.pattern.AbstractRestDTO;

public class FederativeUnitDTO extends AbstractRestDTO {

    private Long id;

    private String name;

    private String initials;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }
}
