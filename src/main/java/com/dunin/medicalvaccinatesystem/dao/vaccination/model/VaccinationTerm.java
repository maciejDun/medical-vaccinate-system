package com.dunin.medicalvaccinatesystem.dao.vaccination.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vaccination_term")
@Data
public class VaccinationTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigserial", updatable = false, insertable = false)
    private Long id;
    private LocalDateTime vaccinationDate;
    private LocalDateTime creationDate;
    @ManyToOne
    private VaccinationFacility vaccinationFacility;
}
