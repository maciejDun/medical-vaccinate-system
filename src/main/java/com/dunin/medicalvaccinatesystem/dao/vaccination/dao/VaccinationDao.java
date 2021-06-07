package com.dunin.medicalvaccinatesystem.dao.vaccination.dao;

import com.dunin.medicalvaccinatesystem.common.exception.TermNotFoundException;
import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUser;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinationTerm;
import com.dunin.medicalvaccinatesystem.dao.vaccination.repo.VaccinatedUserRepo;
import com.dunin.medicalvaccinatesystem.dao.vaccination.repo.VaccinationTermRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VaccinationDao {
    private final VaccinationTermRepo vaccinationTermRepo;
    private final VaccinatedUserRepo vaccinatedUserRepo;

    public List<VaccinationTerm> getAllVaccinationTerms() {
        return vaccinationTermRepo.findAll();
    }

    public VaccinationTerm getVaccinationTermById(Long id) {
        return vaccinationTermRepo.findById(id).orElseThrow(() -> new TermNotFoundException("Term not found"));
    }

    public VaccinatedUser registerVaccUser(UserEntity userEntity, Long termId) {
        VaccinationTerm vaccinationTerm = getVaccinationTermById(termId);

        VaccinatedUser vaccinatedUser = new VaccinatedUser();
        vaccinatedUser.setUserEntity(userEntity);
        vaccinatedUser.setVaccinationTerm(vaccinationTerm);

        vaccinatedUserRepo.save(vaccinatedUser);
        return vaccinatedUser;
    }
}
