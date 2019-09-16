package com.compasso.backend.app.domain.entity;


import com.compasso.backend.app.pattern.repository.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "FederativeUnity")
public class FederativeUnitEntity extends AbstractEntity {

    @Id
    @SequenceGenerator(name = "federative_unit_seq_gen", sequenceName = "city_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="city_seq_gen")
    private Long id;

    private String name;

    private String initials;

    public FederativeUnitEntity() {

    }

    public FederativeUnitEntity(Long id, String initials, String name) {
        this.id = id;
        this.name = name;
        this.initials = initials;
    }

    @Override
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
