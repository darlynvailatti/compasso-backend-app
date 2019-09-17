package com.compasso.backend.app.rest.dto;

import com.compasso.backend.app.rest.pattern.AbstractRestDTO;

import java.time.LocalDate;

public class ClientDTO extends AbstractRestDTO {

    private Long id;

    private String fullName;

    private CityDTO city;

    private GenderDTOEnum gender;

    private LocalDate birthDate;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public GenderDTOEnum getGender() {
        return gender;
    }

    public void setGender(GenderDTOEnum gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
