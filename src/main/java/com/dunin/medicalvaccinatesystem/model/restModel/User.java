package com.dunin.medicalvaccinatesystem.model.restModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String userName;
    private List<Role> roles;
}
