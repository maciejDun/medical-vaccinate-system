package com.dunin.medicalvaccinatesystem.dao.vaccination.repo;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.TermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VaccinationTermRepo extends JpaRepository<TermEntity, Long> {
    boolean existsByVaccinationDateAndFacilityEntityId(LocalDateTime date, Long facilityId);
    boolean existsByFacilityEntityId(Long id);
}

