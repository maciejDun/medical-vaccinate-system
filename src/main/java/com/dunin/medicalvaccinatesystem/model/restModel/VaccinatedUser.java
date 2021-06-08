package com.dunin.medicalvaccinatesystem.model.restModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class VaccinatedUser {
    private Long id;
    private User user;
    private Term term;
}
