package com.dunin.medicalvaccinatesystem.dao.vaccination.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vaccination_term")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TermEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigserial", updatable = false, insertable = false)
    private Long id;
    private LocalDateTime vaccinationDate;
    private LocalDateTime creationDate;
    @ManyToOne
    private FacilityEntity facilityEntity;
}
