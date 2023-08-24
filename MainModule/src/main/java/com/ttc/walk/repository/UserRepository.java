package com.ttc.walk.repository;

import com.ttc.walk.model.entity.UserEntity;
import com.ttc.walk.num.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findUserEntityByUsernameOrPhone(String username, String phone);
    Optional<UserEntity> findByUsernameAndStatus(String username, AccountStatus status);

}
