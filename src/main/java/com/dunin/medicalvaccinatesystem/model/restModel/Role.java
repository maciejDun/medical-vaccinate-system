package com.dunin.medicalvaccinatesystem.model.restModel;

import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
public class Role {
    @NotNull(message = "Role Id cannot be blank")
    Long id;
    @NotNull(message = "Roles cannot be blank")
    Roles roles;

}
