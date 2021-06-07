package com.dunin.medicalvaccinatesystem.buissnessService.mapper;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinationTerm;
import com.dunin.medicalvaccinatesystem.model.restModel.Facility;
import com.dunin.medicalvaccinatesystem.model.restModel.Term;

public class TermMapper {
    public Term map(VaccinationTerm term) {

        Facility facility = getFacility(term);

        return Term.builder()
                   .id(term.getId())
                   .vaccinationDate(term.getVaccinationDate())
                   .facility(facility)
                   .build();
    }

    private Facility getFacility(VaccinationTerm term) {
        FacilityMapper facilityMapper = new FacilityMapper();
        Facility facility = facilityMapper.map(term.getVaccinationFacility());
        return facility;
    }
}
