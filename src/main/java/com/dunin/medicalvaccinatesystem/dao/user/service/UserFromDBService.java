package com.dunin.medicalvaccinatesystem.dao.user.service;

import com.dunin.medicalvaccinatesystem.dao.user.model.UserFromDB;
import com.dunin.medicalvaccinatesystem.dao.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFromDBService {

    @Autowired
    UserRepository userRepository;

    public void processOAuthPostLogin(String username) {
        Optional<UserFromDB> userExists = userRepository.findByUserName(username);

        if (userExists.isEmpty()) {
            UserFromDB user = new UserFromDB();
            user.setUserName(username);
            user.setRoles("ROLE_USER");
            user.setActive(true);

            userRepository.save(user);
        }

    }

}

