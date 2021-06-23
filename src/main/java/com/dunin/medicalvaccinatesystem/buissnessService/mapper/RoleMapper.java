package com.dunin.medicalvaccinatesystem.buissnessService.mapper;

import com.dunin.medicalvaccinatesystem.dao.role.model.RoleEntity;
import com.dunin.medicalvaccinatesystem.model.restModel.Role;

public class RoleMapper {

    public Role map(RoleEntity roleEntity) {
        return Role.builder()
                   .id(roleEntity.getId())
                   .roles(roleEntity.getRoles())
                   .build();
    }
}
