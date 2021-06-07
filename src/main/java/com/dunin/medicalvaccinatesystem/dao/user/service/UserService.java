package com.dunin.medicalvaccinatesystem.dao.user.service;

import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.dao.user.repo.UserRepository;
import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import com.dunin.medicalvaccinatesystem.security.oauth.service.OAuth2AttributeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OAuth2AttributeExtractor oAuth2AttributeExtractor;
    private String username;

    public boolean processOAuthPostLogin() {
        getUsername();
        Optional<UserEntity> userExists = getUserOptional(this.username);

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
        return (role.equals(Roles.ROLE_ADMIN));
    }

    public UserEntity getCurrentUserEntity() {
        return getUserOptional(getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String getUsername() {
        this.username = oAuth2AttributeExtractor.getEmail();
        return this.username;
    }
    //todo add UserDao class to separate layers
    //todo check if term is already taken
    //todo check if user is already registered
    //todo unregister user function
    //todo admin manage terms (delete, update, delete reserved terms)
}

