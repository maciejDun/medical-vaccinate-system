package com.dunin.medicalvaccinatesystem.dao.user.model;

import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigserial", updatable = false, insertable = false)
    private Long id;
    private String userName;
    @Enumerated(EnumType.STRING)
    private Roles roles;
}
