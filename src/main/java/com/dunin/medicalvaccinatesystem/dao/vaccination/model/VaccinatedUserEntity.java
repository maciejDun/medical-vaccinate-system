package com.dunin.medicalvaccinatesystem.dao.vaccination.model;

import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "vaccinated_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccinatedUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigserial", updatable = false, insertable = false)
    private Long id;
    @OneToOne
    private UserEntity userEntity;
    @OneToOne
    private TermEntity termEntity;
}
