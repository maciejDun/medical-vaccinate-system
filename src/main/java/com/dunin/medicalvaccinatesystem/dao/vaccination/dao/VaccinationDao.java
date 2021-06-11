package com.dunin.medicalvaccinatesystem.dao.vaccination.dao;

import com.dunin.medicalvaccinatesystem.common.exception.FKOfFacilityExistInAnotherTableException;
import com.dunin.medicalvaccinatesystem.common.exception.FacilityAlreadyExistException;
import com.dunin.medicalvaccinatesystem.common.exception.FacilityNotExistException;
import com.dunin.medicalvaccinatesystem.common.exception.TermAlreadyExistsException;
import com.dunin.medicalvaccinatesystem.common.exception.TermAlreadyTakenException;
import com.dunin.medicalvaccinatesystem.common.exception.TermNotFoundException;
import com.dunin.medicalvaccinatesystem.common.exception.UserAlreadyRegisteredException;
import com.dunin.medicalvaccinatesystem.common.exception.VaccinatedUserNotFoundException;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.FacilityEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.TermEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUserEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.repo.VaccinatedUserRepo;
import com.dunin.medicalvaccinatesystem.dao.vaccination.repo.VaccinationFacilityRepo;
import com.dunin.medicalvaccinatesystem.dao.vaccination.repo.VaccinationTermRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VaccinationDao {
    private final VaccinationTermRepo vaccinationTermRepo;
    private final VaccinatedUserRepo vaccinatedUserRepo;
    private final VaccinationFacilityRepo vaccinationFacilityRepo;

    public List<TermEntity> getAllVaccinationTerms() {
        return vaccinationTermRepo.findAll();
    }

    public List<TermEntity> getAvailableVaccinationTerms() {
        return vaccinationTermRepo.findAvailableTerms();
    }

    public TermEntity getVaccinationTermById(Long id) {
        return findVaccinationTermOrException(id);
    }

    public VaccinatedUserEntity registerVaccUser(VaccinatedUserEntity vaccinatedUserEntity) {
        vaccinatedUserRepo.save(vaccinatedUserEntity);
        return vaccinatedUserEntity;
    }

    @Transactional
    public void unregisterUser(Long userId) {
        findVaccinatedUserOrException(userId);
        vaccinatedUserRepo.deleteByUserEntityId(userId);
    }

    public void unregisterUserById(Long entityId) {
        VaccinatedUserEntity vaccinatedUserEntity = getUserForAdminUnregistrationOrException(entityId);
        vaccinatedUserRepo.delete(vaccinatedUserEntity);
    }

    public List<VaccinatedUserEntity> getAllVaccinatedUsers() {
        return vaccinatedUserRepo.findAll();
    }

    public VaccinatedUserEntity addVaccinatedUser(VaccinatedUserEntity vaccinatedUserEntity) {
        return vaccinatedUserRepo.save(vaccinatedUserEntity);
    }

    public void deleteTermById(Long termId) {
        findVaccinationTermOrException(termId);
        vaccinationTermRepo.deleteById(termId);
    }

    public TermEntity addTermEntity(TermEntity termEntity) {
        return vaccinationTermRepo.save(termEntity);
    }

    public FacilityEntity getFacilityEntityById(Long facilityId) {
        return getFacilityOrException(facilityId);
    }

    public List<FacilityEntity> getFacilities() {
        return vaccinationFacilityRepo.findAll();
    }

    public void deleteFacilityById(Long id) {
        FacilityEntity facilityEntity = getFacilityEntityById(id);
        vaccinationFacilityRepo.delete(facilityEntity);
    }

    public FacilityEntity addFacilityEntity(FacilityEntity facilityEntity) {
        return vaccinationFacilityRepo.save(facilityEntity);
    }

    public void checkIfTermAlreadyExists(LocalDateTime date, Long facilityId) {
        boolean termExists = vaccinationTermRepo.existsByVaccinationDateAndFacilityEntityId(date, facilityId);
        if (termExists) {
            throw new TermAlreadyExistsException("Cannot create term: term already exist");
        }
    }

    public void checkIfFacilityIsInTermTable(Long id) {
        if (vaccinationTermRepo.existsByFacilityEntityId(id)) {
            throw new FKOfFacilityExistInAnotherTableException("Cant delete facility," +
                    " because FK of facility exists in another table ");
        }
    }

    public void checkIfFacilityAlreadyExist(String city, String country, String state, String address) {
        if (vaccinationFacilityRepo.existsByCityAndCountryAndStateAndAddress(city, country,
                state, address)) {
            throw new FacilityAlreadyExistException("Cannot create facility: facility already exist");
        }
    }

    public void checkIfTermIsTaken(Long termId) {
        if (vaccinatedUserRepo.existsByTermEntityId(termId)) {
            throw new TermAlreadyTakenException("These term is already taken");
        }
    }

    public void checkIfUserAlreadyRegistered(Long userId) {
        if (vaccinatedUserRepo.existsByUserEntityId(userId)) {
            throw new UserAlreadyRegisteredException("User already registered to another term");
        }
    }

    public void checkIfUserIsInVaccinatedUserTable(Long userId) {
        if (vaccinatedUserRepo.existsByUserEntityId(userId)) {
            throw new UserAlreadyRegisteredException("Cant delete user, because FK of user exists in another table ");
        }
    }

    private FacilityEntity getFacilityOrException(Long facilityId) {
        return findFacilityById(facilityId).orElseThrow(() -> new FacilityNotExistException("Facility does not exist"));
    }

    private VaccinatedUserEntity getUserForAdminUnregistrationOrException(Long entityId) {
        return findVaccinatedUserByEntityId(entityId).orElseThrow(
                () -> new VaccinatedUserNotFoundException("Vaccinated user does not exist"));
    }

    private VaccinatedUserEntity findVaccinatedUserOrException(Long userId) {
        return findVaccinatedUser(userId).orElseThrow(
                () -> new VaccinatedUserNotFoundException("You haven't been registered already"));
    }

    private TermEntity findVaccinationTermOrException(Long id) {
        return getTermById(id).orElseThrow(() -> new TermNotFoundException("Term not found"));
    }

    private Optional<FacilityEntity> findFacilityById(Long facilityId) {
        return vaccinationFacilityRepo.findById(facilityId);
    }

    private Optional<VaccinatedUserEntity> findVaccinatedUserByEntityId(Long entityId) {
        return vaccinatedUserRepo.findById(entityId);
    }

    private Optional<VaccinatedUserEntity> findVaccinatedUser(Long userId) {
        return vaccinatedUserRepo.findByUserEntityId(userId);
    }

    private Optional<TermEntity> getTermById(Long id) {
        return vaccinationTermRepo.findById(id);
    }
}
