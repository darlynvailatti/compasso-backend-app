package com.compasso.backend.app.domain.entity;

import com.compasso.backend.app.domain.common.Gender;
import com.compasso.backend.app.pattern.repository.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "Client")
public class ClientEntity extends AbstractEntity {

    @Id
    @SequenceGenerator(name = "city_seq_gen", sequenceName = "city_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="city_seq_gen")
    private Long id;

    private String fullName;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private CityEntity city;

    @Override
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public Period getAge(){
        return Period.between(this.birthDate, LocalDate.now());
    }
}
