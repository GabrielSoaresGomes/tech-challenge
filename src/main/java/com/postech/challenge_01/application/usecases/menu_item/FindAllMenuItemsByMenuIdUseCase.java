package com.postech.challenge_01.application.usecases.menu_item;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemsByMenuIdRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindAllMenuItemsByMenuIdUseCase implements UseCase<MenuItemsByMenuIdRequestDTO, List<MenuItem>> {
    private final IMenuItemGateway gateway;

    @Transactional
    @Override
    public List<MenuItem> execute(MenuItemsByMenuIdRequestDTO request) {
        log.info("Listando itens do menu {}", request.menuId());
        var list = this.gateway.findAllByMenuId(request.menuId(), request.pageable());

        log.info("Itens do menu com ID {} retornados {}", request.menuId(), list);
        return list;
    }
}

