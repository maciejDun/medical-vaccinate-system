package com.dunin.medicalvaccinatesystem.dao.user;

import com.dunin.medicalvaccinatesystem.common.exception.UserNotFoundException;
import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.dao.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository userRepository;

    public UserEntity getUserEntity(String username) {
        return userRepository.findByUserName(username).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void saveUserEntity(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public Optional<UserEntity> getUserEntityOptional(String username) {
        return userRepository.findByUserName(username);
    }
}
