package com.postech.challenge_01.interface_adapter.data_sources.repositories;

import com.postech.challenge_01.dtos.transfer.menu.MenuDTO;
import com.postech.challenge_01.dtos.transfer.menu.NewMenuDTO;

public interface MenuRepository extends CrudRepository<NewMenuDTO, MenuDTO, Long> {
}
