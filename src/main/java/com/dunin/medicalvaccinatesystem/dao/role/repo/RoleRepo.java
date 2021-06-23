package com.dunin.medicalvaccinatesystem.dao.role.repo;

import com.dunin.medicalvaccinatesystem.dao.role.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
}
