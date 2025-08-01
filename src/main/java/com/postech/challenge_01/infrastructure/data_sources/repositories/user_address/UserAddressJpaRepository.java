package com.postech.challenge_01.infrastructure.data_sources.repositories.user_address;

import com.postech.challenge_01.infrastructure.entities.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressJpaRepository extends JpaRepository<UserAddressEntity, Long> {
}
