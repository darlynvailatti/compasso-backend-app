package com.compasso.backend.app.rest.dto.architecture;


import com.compasso.backend.app.pattern.rest.AbstractRestDTO;

public class OrderDTO extends AbstractRestDTO {

    private DirectionEnum direction;

    private String property;

    private boolean ignoreCase;

    public OrderDTO() {
    }

    public OrderDTO(DirectionEnum direction, String property) {
        this.direction = direction;
        this.property = property;
    }

    public OrderDTO(DirectionEnum direction, String property, boolean ignoreCase) {
        this.direction = direction;
        this.property = property;
        this.ignoreCase = ignoreCase;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }
}
