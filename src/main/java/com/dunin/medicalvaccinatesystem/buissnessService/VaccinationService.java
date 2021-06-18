package com.dunin.medicalvaccinatesystem.buissnessService;

import com.dunin.medicalvaccinatesystem.buissnessService.mapper.*;
import com.dunin.medicalvaccinatesystem.dao.role.model.RoleEntity;
import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.dao.VaccinationDao;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.FacilityEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.TermEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUserEntity;
import com.dunin.medicalvaccinatesystem.model.restModel.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaccinationService {
    private final VaccinationDao vaccinationDao;

    private final UserService userService;

    private final TermMapper termMapper = new TermMapper();
    private final VaccinatedUsersMapper vaccinatedUsersMapper = new VaccinatedUsersMapper();
    private final UserMapper userMapper = new UserMapper();
    private final FacilityMapper facilityMapper = new FacilityMapper();
    private final RoleMapper roleMapper = new RoleMapper();

    public List<Term> getAllVaccinationTerms() {
        return vaccinationDao.getAllVaccinationTerms().stream()
                             .map(termMapper::map)
                             .collect(Collectors.toList());
    }

    public List<Term> getAvailableVaccinationTerms() {
        return vaccinationDao.getAvailableVaccinationTerms().stream()
                             .map(termMapper::map)
                             .collect(Collectors.toList());
    }

    public Term returnOneTerm(Long termId) {
        return termMapper.map(vaccinationDao.getVaccinationTermById(termId));
    }

    public VaccinatedUserEntity registerVaccUser(Long termId) {
        UserEntity userEntity = userService.getLoggedInUserEntity();
        Long userId = userEntity.getId();

        checkIfCanRegister(termId, userId);

        TermEntity termEntity = getVaccinationTermEntityById(termId);
        VaccinatedUserEntity vaccinatedUserEntity = createNewVaccinatedUser(userEntity, termEntity);

        VaccinatedUserEntity savedVaccinatedUserEntity = vaccinationDao.registerVaccUser(vaccinatedUserEntity);
        return savedVaccinatedUserEntity;
    }

    public void unregisterUser() {
        UserEntity userEntity = userService.getLoggedInUserEntity();
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

    public VaccinatedUser addVaccinatedUser(Long userId, Long termId) {
        checkIfTermIsTaken(termId);
        checkIfUserAlreadyRegistered(userId);

        UserEntity userEntity = getUserEntityById(userId);
        TermEntity termEntity = getVaccinationTermEntityById(termId);

        VaccinatedUserEntity vaccinatedUserEntity =
                vaccinatedUsersMapper.buildVaccinatedUserEntity(userEntity, termEntity);

        VaccinatedUserEntity savedVaccinatedUserEntity = vaccinationDao.addVaccinatedUser(vaccinatedUserEntity);
        VaccinatedUser mappedToVaccinatedUser =
                vaccinatedUsersMapper.map(savedVaccinatedUserEntity);
        return mappedToVaccinatedUser;
    }

    public List<User> getUsers() {
        return userService.getUsers().stream()
                          .map(userMapper::map)
                          .collect(Collectors.toList());
    }

    public void deleteUserById(Long userId) {
        vaccinationDao.checkIfUserIsInVaccinatedUserTable(userId);
        userService.deleteUserByID(userId);
    }

    public User addUser(UserUpsert user) {
        return mapToUser(getSavedUserEntity(user, getRole(user)));
    }

    private UserEntity getSavedUserEntity(UserUpsert user, RoleEntity roleEntity) {
        return userService.addUserEntity(mapToUserEntity(user, roleEntity));
    }

    private RoleEntity getRole(UserUpsert user) {
        return vaccinationDao.getRole(user.getRoleId());
    }

    public void deleteTermById(Long termId) {
        vaccinationDao.deleteTermById(termId);
    }

    public Term addTerm(TermUpsert term) {
        checkIfTermAlreadyExist(term.getVaccinationDate(), term.getFacilityId());

        FacilityEntity facilityEntity = vaccinationDao.getFacilityEntityById(term.getFacilityId());

        TermEntity termEntity = termMapper.map(term, facilityEntity);

        TermEntity savedTermEntity = vaccinationDao.addTermEntity(termEntity);
        Term mappedToTerm = termMapper.map(savedTermEntity);
        return mappedToTerm;
    }

    public List<Facility> getFacilities() {
        return vaccinationDao.getFacilities().stream()
                             .map(facilityMapper::map)
                             .collect(Collectors.toList());
    }

    public void deleteFacilityById(Long id) {
        vaccinationDao.checkIfFacilityIsInTermTable(id);
        vaccinationDao.deleteFacilityById(id);
    }

    public Facility addFacility(Facility facility) {
        checkIfFacilityAlreadyExist(facility);

        FacilityEntity facilityEntity = facilityMapper.map(facility);

        FacilityEntity savedFacilityEntity = vaccinationDao.addFacilityEntity(facilityEntity);
        Facility mappedToFacility = facilityMapper.map(savedFacilityEntity);
        return mappedToFacility;
    }

    public List<Role> getRoles() {
        return vaccinationDao.getRoles().stream()
                             .map(roleMapper::map)
                             .collect(Collectors.toList());
    }

    public List<User> getNotRegisteredUsers() {
        return userService.getNotRegisteredUsers().stream()
                          .map(userMapper::map)
                          .collect(Collectors.toList());
    }

    private User mapToUser(UserEntity savedUserEntity) {
        return userMapper.map(savedUserEntity);
    }

    private UserEntity mapToUserEntity(UserUpsert user, RoleEntity roleEntity) {
        return userMapper.map(user, roleEntity);
    }

    private UserEntity getUserEntityById(Long userId) {
        return userService.getUserEntityById(userId);
    }

    private TermEntity getVaccinationTermEntityById(Long termId) {
        return vaccinationDao.getVaccinationTermById(termId);
    }

    private VaccinatedUserEntity createNewVaccinatedUser(UserEntity userEntity, TermEntity termEntity) {
        VaccinatedUserEntity vaccinatedUserEntity = new VaccinatedUserEntity();
        vaccinatedUserEntity.setUserEntity(userEntity);
        vaccinatedUserEntity.setTermEntity(termEntity);
        return vaccinatedUserEntity;
    }

    private void checkIfTermAlreadyExist(LocalDateTime date, Long facilityId) {
        vaccinationDao.checkIfTermAlreadyExists(date, facilityId);
    }

    private void checkIfFacilityAlreadyExist(Facility facility) {
        String city = facility.getCity();
        String country = facility.getCountry();
        String state = facility.getState();
        String address = facility.getAddress();

        vaccinationDao.checkIfFacilityAlreadyExist(city, country, state, address);
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
