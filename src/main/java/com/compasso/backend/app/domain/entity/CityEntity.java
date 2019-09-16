package com.compasso.backend.app.domain.entity;

import com.compasso.backend.app.pattern.repository.AbstractEntity;

import javax.persistence.*;


@Entity
@Table(name = "City")
public class CityEntity extends AbstractEntity {

    @Id
    @SequenceGenerator(name = "city_seq_gen", sequenceName = "city_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="city_seq_gen")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private FederativeUnitEntity federativeUnit;

    public CityEntity() {
    }

    public CityEntity(String name, FederativeUnitEntity federativeUnit) {
        this.name = name;
        this.federativeUnit = federativeUnit;
    }

    @Override
    public Long getId() {
        return this.id;
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

    public FederativeUnitEntity getFederativeUnit() {
        return federativeUnit;
    }

    public void setFederativeUnit(FederativeUnitEntity federativeUnity) {
        this.federativeUnit = federativeUnity;
    }
}
