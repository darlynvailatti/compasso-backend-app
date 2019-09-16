package com.compasso.backend.app.rest.dto.architecture;

import com.compasso.backend.app.pattern.rest.AbstractRestDTO;

import java.util.Collection;
import java.util.List;

public class PageDTO<T extends AbstractRestDTO> {

    private Long totalElements;

    private Collection<T> elements;

    public PageDTO() {
    }

    public PageDTO(Long totalElements, Collection<T> elements) {
        this.totalElements = totalElements;
        this.elements = elements;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Collection<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
