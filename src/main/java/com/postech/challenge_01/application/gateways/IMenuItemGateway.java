package com.postech.challenge_01.application.gateways;

import com.postech.challenge_01.domain.MenuItem;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMenuItemGateway extends CrudGateway<MenuItem, Long> {
    List<MenuItem> findAllByMenuId(Long menuId, Pageable pageable);
}
