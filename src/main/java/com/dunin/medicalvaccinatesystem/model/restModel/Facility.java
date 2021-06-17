package com.dunin.medicalvaccinatesystem.model.restModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Builder
public class Facility {
    private Long id;
    @NotBlank(message = "Country cannot be blank")
    private String country;
    @NotBlank(message = "State cannot be blank")
    private String state;
    @NotBlank(message = "City cannot be blank")
    private String city;
    @NotBlank(message = "Address cannot be blank")
    private String address;
}
