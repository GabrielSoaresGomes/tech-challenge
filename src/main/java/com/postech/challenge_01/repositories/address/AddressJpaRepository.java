package com.postech.challenge_01.repositories.address;

import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {
    @Query("""
        SELECT a.address FROM UserAddressEntity a
        WHERE a.user.id = :userId
    """)
    List<AddressEntity> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("""
        SELECT a.address FROM UserAddressEntity a
        WHERE a.address.id = :id AND a.user.id = :userId
    """)
    Optional<AddressEntity> findByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    @Modifying
    @Query("DELETE FROM UserAddressEntity a WHERE a.user.id = :user_id")
    void deleteByUserId(@Param("user_id") Long userId);

}
