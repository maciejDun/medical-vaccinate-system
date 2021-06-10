package com.dunin.medicalvaccinatesystem.dao.vaccination.repo;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.FacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationFacilityRepo extends JpaRepository<FacilityEntity, Long> {
    boolean existsByCityAndCountryAndStateAndAddress(String city, String country, String state,
                                                             String address);
}
