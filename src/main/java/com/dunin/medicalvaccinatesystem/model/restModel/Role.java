package com.dunin.medicalvaccinatesystem.model.restModel;

import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Role {
    Roles roles;
}
