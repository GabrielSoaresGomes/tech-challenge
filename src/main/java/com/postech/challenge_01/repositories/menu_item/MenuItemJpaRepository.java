package com.postech.challenge_01.repositories.menu_item;

import com.postech.challenge_01.entities.MenuItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MenuItemJpaRepository extends JpaRepository<MenuItemEntity, Long> {
    Page<MenuItemEntity> findAllByMenuId(Long menuId, Pageable pageable);
}
