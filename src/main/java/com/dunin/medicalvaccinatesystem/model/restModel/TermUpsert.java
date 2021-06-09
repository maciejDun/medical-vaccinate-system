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
    @NotNull
    private LocalDateTime vaccinationDate;
    @NotNull
    private Long facilityId;
}
