package com.dunin.medicalvaccinatesystem.buissnessService.mapper;


import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUserEntity;
import com.dunin.medicalvaccinatesystem.dao.vaccination.model.TermEntity;
import com.dunin.medicalvaccinatesystem.model.restModel.Term;
import com.dunin.medicalvaccinatesystem.model.restModel.User;
import com.dunin.medicalvaccinatesystem.model.restModel.VaccinatedUser;

public class VaccinatedUsersMapper {



    public VaccinatedUser map(VaccinatedUserEntity vaccinatedUserEntity) {

        UserMapper userMapper = new UserMapper();
        TermMapper termMapper = new TermMapper();

        User user = userMapper.map(vaccinatedUserEntity.getUserEntity());
        Term term = termMapper.map(vaccinatedUserEntity.getTermEntity());

        return VaccinatedUser.builder()
                             .id(vaccinatedUserEntity.getId())
                             .user(user)
                             .term(term)
                             .build();
    }

    public VaccinatedUserEntity map(VaccinatedUser vaccinatedUser) {
        UserMapper userMapper = new UserMapper();
        TermMapper termMapper = new TermMapper();

        UserEntity userEntity = userMapper.map(vaccinatedUser.getUser());
        TermEntity termEntity = termMapper.map(vaccinatedUser.getTerm());

        return VaccinatedUserEntity.builder()
                .userEntity(userEntity)
                .termEntity(termEntity)
                .build();
    }

}
