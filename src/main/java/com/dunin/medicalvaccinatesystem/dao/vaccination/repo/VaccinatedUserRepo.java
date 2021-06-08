package com.dunin.medicalvaccinatesystem.dao.vaccination.repo;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VaccinatedUserRepo extends JpaRepository<VaccinatedUserEntity, Long> {
    boolean existsByTermEntityId(Long termId);
    boolean existsByUserEntityId(Long userId);
    void deleteByUserEntityId(Long userId);
    Optional<VaccinatedUserEntity> findByUserEntityId(Long userId);
}
