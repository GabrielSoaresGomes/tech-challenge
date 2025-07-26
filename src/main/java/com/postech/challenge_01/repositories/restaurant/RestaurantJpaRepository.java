package com.postech.challenge_01.repositories.restaurant;

import com.postech.challenge_01.entities.restaurant.RestaurantEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Long> {

    @Query("""
        SELECT r FROM RestaurantEntity r 
        WHERE :now BETWEEN r.startTime AND r.endTime
    """)
    List<RestaurantEntity> findAllOpen(@Param("now")LocalTime now, Pageable pageable);
}