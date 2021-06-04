package com.dunin.medicalvaccinatesystem.dao.vaccination.model;

import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vaccinated_user")
@Data
public class VaccinatedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "bigserial", updatable = false, insertable = false)
    private Integer id;
    @OneToOne
    private UserEntity userEntity;
    @OneToOne
    private VaccinationTerm vaccinationTerm;
}
