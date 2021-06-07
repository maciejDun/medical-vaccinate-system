package com.dunin.medicalvaccinatesystem.buissnessService.mapper;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinationFacility;
import com.dunin.medicalvaccinatesystem.model.restModel.Facility;

public class FacilityMapper {
    public Facility map(VaccinationFacility facility) {
        return Facility.builder()
                       .id(facility.getId())
                       .country(facility.getCountry())
                       .state(facility.getState())
                       .city(facility.getCity())
                       .streetAndNumber(facility.getStreetAndNumber())
                       .build();
    }
}
