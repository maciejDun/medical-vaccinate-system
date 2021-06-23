package com.dunin.medicalvaccinatesystem.buissnessService.mapper;

import com.dunin.medicalvaccinatesystem.dao.role.model.RoleEntity;
import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.model.restModel.Role;
import com.dunin.medicalvaccinatesystem.model.restModel.User;
import com.dunin.medicalvaccinatesystem.model.restModel.UserUpsert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public User map(UserEntity userEntity) {
        return User.builder()
                   .id(userEntity.getId())
                   .userName(userEntity.getUserName())
                   .roles(getRoles(userEntity))
                   .build();
    }

    public UserEntity map(UserUpsert user, RoleEntity role) {
        return UserEntity.builder()
                         .userName(user.getUserName())
                         .roles(getRoles(role))
                         .build();
    }

    private List<Role> getRoles(UserEntity userEntity) {
        RoleMapper roleMapper = new RoleMapper();

        return userEntity.getRoles().stream()
                         .map(roleMapper::map)
                         .collect(Collectors.toList());
    }

    private List<RoleEntity> getRoles(RoleEntity role) {
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

    public UserEntity mapToUpdate(UserUpsert user, RoleEntity roleEntity) {
        return UserEntity.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .roles(getRoles(roleEntity))
                .build();
    }
}
