package com.dunin.medicalvaccinatesystem.dao.user.repo;

import com.dunin.medicalvaccinatesystem.dao.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);

    @Query(value = "select us.id, us.user_name\n" +
            "from users as us\n" +
            "left join vaccinated_user vu on us.id = vu.user_entity_id\n" +
            "where vu.user_entity_id is null", nativeQuery = true)
    List<UserEntity> findAllNotRegisteredUsers();
}
