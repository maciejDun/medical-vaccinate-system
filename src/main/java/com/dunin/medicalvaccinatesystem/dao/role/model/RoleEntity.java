package com.dunin.medicalvaccinatesystem.dao.role.model;

import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role_entity")
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigserial", updatable = false, insertable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    Roles roles;
}
