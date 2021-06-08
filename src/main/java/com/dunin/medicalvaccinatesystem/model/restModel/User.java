package com.dunin.medicalvaccinatesystem.model.restModel;

import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String userName;
    private Roles roles;
}
