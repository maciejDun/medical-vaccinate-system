package com.dunin.medicalvaccinatesystem.buissnessService;

import com.dunin.medicalvaccinatesystem.buissnessService.mapper.TermMapper;
import com.dunin.medicalvaccinatesystem.buissnessService.mapper.VaccinatedUsersMapper;
import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.dao.VaccinationDao;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUserEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.TermEntity;
import com.dunin.medicalvaccinatesystem.model.restModel.Term;
import com.dunin.medicalvaccinatesystem.model.restModel.VaccinatedUser;
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
    private final VaccinatedUsersMapper vaccinatedUsersMapper = new VaccinatedUsersMapper();

    public List<Term> getAllVaccinationTerms() {
        return vaccinationDao.getAllVaccinationTerms().stream()
                             .map(termMapper::map)
                             .collect(Collectors.toList());
    }

    public Term returnOneTerm(Long termId) {
        return termMapper.map(vaccinationDao.getVaccinationTermById(termId));
    }

    public VaccinatedUserEntity registerVaccUser(Long termId) {
        UserEntity userEntity = userService.getCurrentUserEntity();
        Long userId = userEntity.getId();

        checkIfCanRegister(termId, userId);

        TermEntity termEntity = getVaccinationTermById(termId);
        VaccinatedUserEntity vaccinatedUserEntity = getNewVaccinatedUser(userEntity, termEntity);

        return vaccinationDao.registerVaccUser(vaccinatedUserEntity);
    }

    public void unregisterUser() {
        UserEntity userEntity = userService.getCurrentUserEntity();
        Long userId = userEntity.getId();

        vaccinationDao.unregisterUser(userId);
    }

    public void unregisterUser(Long vaccinatedUserId) {
        vaccinationDao.unregisterUserById(vaccinatedUserId);
    }

    public List<VaccinatedUser> getAllVaccinatedUsers() {
        return vaccinationDao.getAllVaccinatedUsers().stream()
                .map(vaccinatedUsersMapper::map)
                .collect(Collectors.toList());
    }
    //todo admin manage terms (delete and create users?, create and delete term?)

    public VaccinatedUser addVaccinatedUser(VaccinatedUser vaccinatedUser) {
        VaccinatedUserEntity vaccinatedUserEntity = vaccinatedUsersMapper.map(vaccinatedUser);

        return vaccinatedUsersMapper.map(vaccinationDao.addVaccinatedUser(vaccinatedUserEntity));
    }

    private TermEntity getVaccinationTermById(Long termId) {
        return vaccinationDao.getVaccinationTermById(termId);
    }

    private VaccinatedUserEntity getNewVaccinatedUser(UserEntity userEntity, TermEntity termEntity) {
        VaccinatedUserEntity vaccinatedUserEntity = new VaccinatedUserEntity();
        vaccinatedUserEntity.setUserEntity(userEntity);
        vaccinatedUserEntity.setTermEntity(termEntity);
        return vaccinatedUserEntity;
    }

    private void checkIfCanRegister(Long termId, Long userId) {
        checkIfTermIsTaken(termId);
        checkIfUserAlreadyRegistered(userId);
    }

    private void checkIfTermIsTaken(Long termId) {
        vaccinationDao.checkIfTermIsTaken(termId);
    }

    private void checkIfUserAlreadyRegistered(Long userId) {
        vaccinationDao.checkIfUserAlreadyRegistered(userId);
    }
}
