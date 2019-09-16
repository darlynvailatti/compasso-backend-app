package com.compasso.backend.app.rest.dto;

import com.compasso.backend.app.pattern.rest.AbstractRestDTO;

import java.time.LocalDate;

public class StudentDTO extends AbstractRestDTO {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private LocalDate registerDate;

    public StudentDTO() {
    }

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

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
