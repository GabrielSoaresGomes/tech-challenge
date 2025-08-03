package com.postech.challenge_01.interface_adapter.data_sources.repositories;

import com.postech.challenge_01.dtos.transfer.menu_item.MenuItemDTO;
import com.postech.challenge_01.dtos.transfer.menu_item.NewMenuItemDTO;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<NewMenuItemDTO, MenuItemDTO, Long> {
    List<MenuItemDTO> findAllByMenuId(Long menuId, int size, long offset);
}
