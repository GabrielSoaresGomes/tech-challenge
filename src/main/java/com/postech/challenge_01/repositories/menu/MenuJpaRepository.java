package com.postech.challenge_01.repositories.menu;

import com.postech.challenge_01.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MenuJpaRepository extends JpaRepository<MenuEntity, Long> {
}
