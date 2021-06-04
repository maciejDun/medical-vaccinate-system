package com.dunin.medicalvaccinatesystem.dao.user.model;

import com.dunin.medicalvaccinatesystem.dao.vaccination.model.VaccinatedUser;
import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, insertable = false)
    private UUID id;
    private String userName;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    @OneToOne(mappedBy = "userEntity")
    VaccinatedUser vaccinatedUser;
}
