package com.dunin.medicalvaccinatesystem.buissnessService;

import com.dunin.medicalvaccinatesystem.dao.user.UserDao;
import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import com.dunin.medicalvaccinatesystem.security.oauth.service.OAuth2AttributeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final OAuth2AttributeExtractor oAuth2AttributeExtractor;
    private final UserDao userDao;

    private String username;

    public boolean processOAuthPostLogin() {
        getUsername();

        Optional<UserEntity> userExists = userDao.getUserEntityOptional(this.username);

        if (userExists.isEmpty()) {
            UserEntity user = getNewUserEntity();
            userDao.saveUserEntity(user);

            return false;
        } else {
            return checkIfAdmin(userExists);
        }
    }

    public UserEntity getLoggedInUserEntity() {
        return userDao.getUserEntityByUsername(getUsername());
    }

    public UserEntity getUserEntityById(Long userId) {
        return userDao.getUserEntityById(userId);
    }

    public List<UserEntity> getUsers() {
        return userDao.getUsers();
    }

    public void deleteUserByID(Long userId) {
        UserEntity userEntity = userDao.getUserEntityById(userId);
        userDao.deleteUser(userEntity);
    }

    public UserEntity addUserEntity(UserEntity userEntity) {
        checkIfUsernameExists(userEntity.getUserName());
        return userDao.addUserEntity(userEntity);
    }

    private String getUsername() {
        this.username = oAuth2AttributeExtractor.getEmail();
        return this.username;
    }

    private boolean checkIfAdmin(Optional<UserEntity> userExists) {
        Roles role = userExists.get().getRoles();
        return (role.equals(Roles.ROLE_ADMIN));
    }

    private void checkIfUsernameExists(String userName) {
        userDao.checkIfExistsByUsername(userName);
    }

    private UserEntity getNewUserEntity() {
        UserEntity user = new UserEntity();
        user.setUserName(this.username);
        user.setRoles(Roles.ROLE_USER);
        return user;
    }
}

