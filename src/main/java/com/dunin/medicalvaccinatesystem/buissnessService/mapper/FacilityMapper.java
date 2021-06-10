package com.dunin.medicalvaccinatesystem.buissnessService.mapper;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.FacilityEntity;
import com.dunin.medicalvaccinatesystem.model.restModel.Facility;

public class FacilityMapper {
    public Facility map(FacilityEntity facility) {
        return Facility.builder()
                       .id(facility.getId())
                       .country(facility.getCountry())
                       .state(facility.getState())
                       .city(facility.getCity())
                       .address(facility.getAddress())
                       .build();
    }

    public FacilityEntity map(Facility facility) {
        return FacilityEntity.builder()
                .id(facility.getId())
                .country(facility.getCountry())
                .state(facility.getState())
                .city(facility.getCity())
                .address(facility.getAddress())
                .build();
    }
}
