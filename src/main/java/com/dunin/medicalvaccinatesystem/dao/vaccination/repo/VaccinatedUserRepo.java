package com.dunin.medicalvaccinatesystem.dao.vaccination.repo;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinatedUserRepo extends JpaRepository<VaccinatedUser, Long> {
}
