package com.dunin.medicalvaccinatesystem.model.restModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
public class Facility {
    private Long id;
    @NotNull(message = "Country cannot be blank")
    private String country;
    @NotNull(message = "State cannot be blank")
    private String state;
    @NotNull(message = "City cannot be blank")
    private String city;
    @NotNull(message = "Address cannot be blank")
    private String address;
}
