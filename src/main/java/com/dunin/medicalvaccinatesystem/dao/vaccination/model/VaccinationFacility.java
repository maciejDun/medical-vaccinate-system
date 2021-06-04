package com.dunin.medicalvaccinatesystem.dao.vaccination.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vaccination_facility")
@Data
public class VaccinationFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "bigserial", updatable = false, insertable = false)
    private Integer id;
    private String country;
    private String state;
    private String city;
    private String streetAndNumber;
}
