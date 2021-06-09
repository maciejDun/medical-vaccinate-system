package com.dunin.medicalvaccinatesystem.model.restModel;

import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Builder
@Getter
@AllArgsConstructor
public class User {
    private Long id;
    @Email(message = "Email format is inappropriate")
    private String userName;
    @NotNull
    private Roles roles;
}
