package com.dunin.medicalvaccinatesystem.model.restModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Facility {
    private Long id;
    private String country;
    private String state;
    private String city;
    private String streetAndNumber;
}
