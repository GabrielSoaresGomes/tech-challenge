package com.postech.challenge_01.repositories.user;

import com.postech.challenge_01.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    @Query("""
        SELECT u FROM UserEntity u
        WHERE u.login = :login
    """)
    Optional<UserEntity> findByLogin(@Param("login") String login);

    @Modifying
    @Query("UPDATE UserEntity u SET u.password = :password WHERE u.id = :id")
    int updatePassword(@Param("id") Long id, @Param("password") String password);
}
