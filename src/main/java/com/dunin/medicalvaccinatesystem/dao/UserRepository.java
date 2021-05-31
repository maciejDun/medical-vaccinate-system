package com.dunin.medicalvaccinatesystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserFromDB, UUID> {
    Optional<UserFromDB> findByUserName(String userName);
}
