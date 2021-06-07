package com.dunin.medicalvaccinatesystem.dao.user.model;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUser;
import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigserial", updatable = false, insertable = false)
    private Long id;
    private String userName;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    @OneToOne(mappedBy = "userEntity")
    VaccinatedUser vaccinatedUser;
}
