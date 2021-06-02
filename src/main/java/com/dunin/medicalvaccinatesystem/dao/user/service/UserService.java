package com.dunin.medicalvaccinatesystem.dao.user.service;

import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.dao.user.repo.UserRepository;
import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean processOAuthPostLogin(String username) {
        Optional<UserEntity> userExists = getUserOptional(username);

        if (userExists.isEmpty()) {
            UserEntity user = new UserEntity();
            user.setUserName(username);
            user.setRoles(Roles.ROLE_USER);

            userRepository.save(user);

            return false;
        } else {
            return checkIfAdmin(userExists);
        }
    }

    private Optional<UserEntity> getUserOptional(String username) {
        return userRepository.findByUserName(username);
    }

    public boolean checkIfAdmin(Optional<UserEntity> userExists) {
        Roles role = userExists.get().getRoles();
        if (role.equals(Roles.ROLE_ADMIN)) return true;
        return false;
    }

}

