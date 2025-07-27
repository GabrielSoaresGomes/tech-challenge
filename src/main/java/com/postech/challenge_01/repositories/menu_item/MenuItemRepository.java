package com.postech.challenge_01.repositories.menu_item;

import com.postech.challenge_01.domains.MenuItem;
import com.postech.challenge_01.repositories.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAllByMenuId(Long menuId, int size, long offset);
}
