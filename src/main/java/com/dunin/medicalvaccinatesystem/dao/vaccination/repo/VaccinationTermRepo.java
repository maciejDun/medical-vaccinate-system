package com.dunin.medicalvaccinatesystem.dao.vaccination.repo;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.TermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VaccinationTermRepo extends JpaRepository<TermEntity, Long> {
    boolean existsByVaccinationDateAndFacilityEntityId(LocalDateTime date, Long facilityId);
    boolean existsByFacilityEntityId(Long id);
    @Query(value = "select vt.id, vt.creation_date,vt.vaccination_date, vt.facility_entity_id " +
            "from vaccination_term as vt\n" +
            "left join vaccinated_user vu on vt.id = vu.term_entity_id\n" +
            "where vu.id is null", nativeQuery = true)
    List<TermEntity> findAvailableTerms();
}

