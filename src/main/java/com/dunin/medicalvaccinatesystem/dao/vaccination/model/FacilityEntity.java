package com.dunin.medicalvaccinatesystem.dao.vaccination.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vaccination_facility")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigserial", updatable = false, insertable = false)
    private Long id;
    private String country;
    private String state;
    private String city;
    private String address;
}
