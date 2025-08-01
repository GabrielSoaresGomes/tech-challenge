package com.postech.challenge_01.infrastructure.data_sources.repositories.menu_item;

import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.CrudRepositoryDeprecated;

import java.util.List;

// TODO: Remover
public interface MenuItemRepository extends CrudRepositoryDeprecated<MenuItem, Long> {
    List<MenuItem> findAllByMenuId(Long menuId, int size, long offset);
}
