package com.dunin.medicalvaccinatesystem.buissnessService.mapper;

import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.model.restModel.User;

public class UserMapper {
    public User map(UserEntity userEntity) {
        return User.builder()
                   .id(userEntity.getId())
                   .userName(userEntity.getUserName())
                   .roles(userEntity.getRoles())
                   .build();
    }

    public UserEntity map(User user) {
        return UserEntity.builder()
                .userName(user.getUserName())
                .roles(user.getRoles())
                .build();
    }
}
