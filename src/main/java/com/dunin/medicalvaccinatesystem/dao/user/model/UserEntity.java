package com.dunin.medicalvaccinatesystem.dao.user.model;

import com.dunin.medicalvaccinatesystem.dao.role.model.RoleEntity;
import com.dunin.medicalvaccinatesystem.model.roles.Roles;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_entity_id"))
    private List<RoleEntity> roles;
}
