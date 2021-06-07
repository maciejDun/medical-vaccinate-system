package com.dunin.medicalvaccinatesystem.buissnessService;

import com.dunin.medicalvaccinatesystem.buissnessService.mapper.TermMapper;
import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.dao.user.service.UserService;
import com.dunin.medicalvaccinatesystem.dao.vaccination.dao.VaccinationDao;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUser;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinationTerm;
import com.dunin.medicalvaccinatesystem.model.restModel.Term;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaccinationService {

    private final VaccinationDao vaccinationDao;
    private final UserService userService;
    private final TermMapper termMapper = new TermMapper();

    public List<Term> getAllVaccinationTerms() {
        return vaccinationDao.getAllVaccinationTerms().stream()
                             .map(termMapper::map)
                             .collect(Collectors.toList());
    }

    public Term returnOneTerm(Long termId) {
        return termMapper.map(vaccinationDao.getVaccinationTermById(termId));
    }

    public VaccinatedUser registerVaccUser(Long termID) {
        UserEntity userEntity = userService.getCurrentUserEntity();

        return vaccinationDao.registerVaccUser(userEntity, termID);
    }
}
