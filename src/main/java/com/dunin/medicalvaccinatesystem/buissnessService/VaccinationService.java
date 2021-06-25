package com.dunin.medicalvaccinatesystem.buissnessService;

import com.dunin.medicalvaccinatesystem.buissnessService.mapper.*;
import com.dunin.medicalvaccinatesystem.common.exception.VaccinatedUserNotFoundException;
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
        return mapToTerm(vaccinationDao.getVaccinationTermById(termId));
    }

    public Term registerVaccUser(Long termId) {
        checkIfCanRegister(termId, getLoggedInId());

        vaccinationDao.registerVaccUser(createNewVaccinatedUser(getLoggedInUserEntity(),
                getVaccinationTermEntityById(termId)));

        return returnOneTerm(termId);
    }

    private Long getLoggedInId() {
        return getLoggedInUserEntity().getId();
    }

    public void unregisterUser() {
        vaccinationDao.unregisterUser(getLoggedInId());
    }

    private UserEntity getLoggedInUserEntity() {
        return userService.getLoggedInUserEntity();
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

        return mapToVaccinatedUser(addVaccinatedUserInDao
                (getUserEntityById(userId), getVaccinationTermEntityById(termId)));
    }

    private VaccinatedUserEntity addVaccinatedUserInDao(UserEntity userEntity, TermEntity termEntity) {
        return vaccinationDao.addVaccinatedUser(buildVaccinatedUserEntity(userEntity, termEntity));
    }

    private VaccinatedUser mapToVaccinatedUser(VaccinatedUserEntity savedVaccinatedUserEntity) {
        return vaccinatedUsersMapper.map(savedVaccinatedUserEntity);
    }

    private VaccinatedUserEntity buildVaccinatedUserEntity(UserEntity userEntity, TermEntity termEntity) {
        return vaccinatedUsersMapper.buildVaccinatedUserEntity(userEntity, termEntity);
    }

    public List<User> getUsers() {
        return userService.getUsers().stream()
                          .map(userMapper::map)
                          .collect(Collectors.toList());
    }

    public void deleteUserById(Long userId) {
        userService.deleteUserByID(checkIfUserIsInVaccinatedUserTable(userId));
    }

    private Long checkIfUserIsInVaccinatedUserTable(Long userId) {
        return vaccinationDao.checkIfUserIsInVaccinatedUserTable(userId);
    }

    public User addUser(UserUpsert user) {
        return mapToUser(getSavedUserEntity(user, getRole(user)));
    }

    public User updateUser(UserUpsert user) {
        return mapToUser(getUpdatedUserEntity(user, getRole(user)));
    }

    private UserEntity getSavedUserEntity(UserUpsert user, RoleEntity roleEntity) {
        return userService.addUserEntity(mapToUserEntity(user, roleEntity));
    }

    private UserEntity getUpdatedUserEntity(UserUpsert user, RoleEntity roleEntity) {
        return userService.updateUserEntity(mapToUpdateUserEntity(user, roleEntity));
    }

    private RoleEntity getRole(UserUpsert user) {
        return vaccinationDao.getRole(user.getRoleId());
    }

    public void deleteTermById(Long termId) {
        vaccinationDao.deleteTermById(termId);
    }

    public Term addTerm(TermUpsert term) {
        checkIfTermAlreadyExist(term.getVaccinationDate(), term.getFacilityId());

        FacilityEntity facilityEntity = getFacilityEntityById(term);

        return mapToTerm(addTermEntity(mapToTermEntity(term, facilityEntity)));
    }

    private FacilityEntity getFacilityEntityById(TermUpsert term) {
        return vaccinationDao.getFacilityEntityById(term.getFacilityId());
    }

    private TermEntity mapToTermEntity(TermUpsert term, FacilityEntity facilityEntity) {
        return termMapper.map(term, facilityEntity);
    }

    private TermEntity addTermEntity(TermEntity termEntity) {
        return vaccinationDao.addTermEntity(termEntity);
    }

    private Term mapToTerm(TermEntity savedTermEntity) {
        return termMapper.map(savedTermEntity);
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
        FacilityEntity facilityEntity = checkIfFacilityAlreadyExist(facility);

        FacilityEntity savedFacilityEntity = addFacilityEntity(facilityEntity);
        return mapToFacility(savedFacilityEntity);
    }

    private FacilityEntity mapToFacilityEntity(Facility facility) {
        return facilityMapper.map(facility);
    }

    private FacilityEntity addFacilityEntity(FacilityEntity facilityEntity) {
        return vaccinationDao.addFacilityEntity(facilityEntity);
    }

    private Facility mapToFacility(FacilityEntity savedFacilityEntity) {
        return facilityMapper.map(savedFacilityEntity);
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

    public User getLoggedInUser() {
        return mapToUser(getLoggedInUserEntity());
    }

    public Term getTermIfRegistered() {
        try {
            VaccinatedUserEntity vaccinatedUser = vaccinationDao.findVaccinatedUserOrException(getLoggedInId());
            TermEntity termEntity = vaccinatedUser.getTermEntity();
            return mapToTerm(termEntity);
        } catch (VaccinatedUserNotFoundException ex) {
            return null;
        }
    }

    private User mapToUser(UserEntity savedUserEntity) {
        return userMapper.map(savedUserEntity);
    }

    private UserEntity mapToUserEntity(UserUpsert user, RoleEntity roleEntity) {
        return userMapper.map(user, roleEntity);
    }

    private UserEntity mapToUpdateUserEntity(UserUpsert user, RoleEntity roleEntity) {
        return userMapper.mapToUpdate(user, roleEntity);
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

    private FacilityEntity checkIfFacilityAlreadyExist(Facility facility) {
        String city = facility.getCity();
        String country = facility.getCountry();
        String state = facility.getState();
        String address = facility.getAddress();

        checkIfFacilityAlreadyExist(city, country, state, address);

        return mapToFacilityEntity(facility);
    }

    private void checkIfFacilityAlreadyExist(String city, String country, String state, String address) {
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
