package com.dunin.medicalvaccinatesystem.model.restModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class TermUpsert {
    @NotNull(message = "Vaccination date cannot be blank")
    private LocalDateTime vaccinationDate;
    @NotNull(message = "Facility cannot be blank")
    private Long facilityId;
}
