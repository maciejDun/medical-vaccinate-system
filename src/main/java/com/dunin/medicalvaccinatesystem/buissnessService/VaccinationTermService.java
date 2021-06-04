package com.dunin.medicalvaccinatesystem.buissnessService;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinationTerm;
import com.dunin.medicalvaccinatesystem.dao.vaccination.repo.VaccinationTermRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccinationTermService {

    private final VaccinationTermRepo vaccinationTermRepo;

    public VaccinationTerm getVaccinationTermById(Integer id) {
        return vaccinationTermRepo.findById(id).get();
        //todo throw custom exception
    }
}
