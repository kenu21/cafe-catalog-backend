package com.cafes.cafes.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "street_id", nullable = false)
    private StreetEntity streetEntity;

    @Column(name = "building_number", nullable = false, length = 20)
    private String buildingNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StreetEntity getStreetEntity() {
        return streetEntity;
    }

    public void setStreetEntity(StreetEntity streetEntity) {
        this.streetEntity = streetEntity;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
