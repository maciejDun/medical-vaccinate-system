package com.dunin.medicalvaccinatesystem.buissnessService;

import com.dunin.medicalvaccinatesystem.dao.role.model.RoleEntity;
import com.dunin.medicalvaccinatesystem.dao.user.UserDao;
import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import com.dunin.medicalvaccinatesystem.security.oauth.service.OAuth2AttributeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final OAuth2AttributeExtractor oAuth2AttributeExtractor;
    private final UserDao userDao;


    public boolean isAdmin(UserEntity user) {
        return userHasAdminRole(user);
    }

    public UserEntity createIfNotExist(Optional<UserEntity> user) {
        return user.orElseGet(() -> createNewUserEntity(getUsername()));
    }

    public Optional<UserEntity> getUserEntityOptional() {
        return userDao.getUserEntityOptional(getUsername());
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
        return userDao.saveUserEntity(userEntity);
    }

    public UserEntity updateUserEntity(UserEntity userEntity) {
        UserEntity userToUpdate = userDao.getUserEntityById(userEntity.getId());
        userToUpdate.setUserName(userEntity.getUserName());
        userToUpdate.setRoles(userEntity.getRoles());

        return userDao.saveUserEntity(userToUpdate);
    }

    public List<UserEntity> getNotRegisteredUsers() {
        return userDao.getNotRegisteredUsers();
    }

    private String getUsername() {
        String username = oAuth2AttributeExtractor.getEmail();
        return username;
    }

    private boolean userHasAdminRole(UserEntity user) {
        List<RoleEntity> roles = user.getRoles();
        RoleEntity roleAdmin = new RoleEntity(1L, Roles.ROLE_ADMIN);
        return (roles.contains(roleAdmin));
    }

    private void checkIfUsernameExists(String userName) {
        userDao.checkIfExistsByUsername(userName);
    }

    private UserEntity createNewUserEntity(String username) {
        UserEntity user = new UserEntity();
        user.setUserName(username);
        user.setRoles(createRoleList());
        userDao.saveUserEntity(user);
        return user;
    }

    private List<RoleEntity> createRoleList() {
        List<RoleEntity> roleList = new ArrayList<>();
        RoleEntity roleUser = new RoleEntity(2L, Roles.ROLE_USER);
        roleList.add(roleUser);
        return roleList;
    }
}

