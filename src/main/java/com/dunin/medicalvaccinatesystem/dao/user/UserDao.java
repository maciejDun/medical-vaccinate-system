package com.dunin.medicalvaccinatesystem.dao.user;

import com.dunin.medicalvaccinatesystem.common.exception.UserAlreadyExistsException;
import com.dunin.medicalvaccinatesystem.common.exception.UserNotFoundException;
import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.dao.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository userRepository;

    public UserEntity getUserEntityByUsername(String username) {
        return userRepository.findByUserName(username).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UserEntity getUserEntityById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UserEntity saveUserEntity(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> getUserEntityOptional(String username) {
        return userRepository.findByUserName(username);
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public List<UserEntity> getNotRegisteredUsers() {
        return userRepository.findAllNotRegisteredUsers();
    }

    public void deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    public void checkIfExistsByUsername(String userName) {
        Optional<UserEntity> optional = getUserEntityOptional(userName);
        if (optional.isPresent()) {
            throw new UserAlreadyExistsException("Cant create: username already taken");
        }
    }
}
