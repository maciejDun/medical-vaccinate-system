package com.dunin.medicalvaccinatesystem.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserFromDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, insertable = false)
    private UUID id;
    private String userName;
    private String password;
    private Boolean active;
    private String roles;
}
