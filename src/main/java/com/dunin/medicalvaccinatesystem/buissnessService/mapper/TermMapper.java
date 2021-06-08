package com.dunin.medicalvaccinatesystem.buissnessService.mapper;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.FacilityEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.TermEntity;
import com.dunin.medicalvaccinatesystem.model.restModel.Facility;
import com.dunin.medicalvaccinatesystem.model.restModel.Term;

public class TermMapper {
    public Term map(TermEntity termEntity) {

        Facility facility = getFacility(termEntity);

        return Term.builder()
                   .id(termEntity.getId())
                   .vaccinationDate(termEntity.getVaccinationDate())
                   .facility(facility)
                   .build();
    }

    public TermEntity map(Term term) {

        FacilityMapper facilityMapper = new FacilityMapper();

        FacilityEntity facilityEntity = facilityMapper.map(term.getFacility());

        return TermEntity.builder()
                .vaccinationDate(term.getVaccinationDate())
                .facilityEntity(facilityEntity)
                .build();
    }

    private Facility getFacility(TermEntity termEntity) {
        FacilityMapper facilityMapper = new FacilityMapper();
        Facility facility = facilityMapper.map(termEntity.getFacilityEntity());
        return facility;
    }
}
