package com.dunin.medicalvaccinatesystem.model.restModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
public class UserUpsert {
    @NotNull
    private Long id;
    @Email(message = "Email format is inappropriate or blank")
    private String userName;
    @NotNull(message = "Role cannot be blank")
    private Long roleId;
}
