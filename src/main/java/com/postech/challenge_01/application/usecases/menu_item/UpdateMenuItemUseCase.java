package com.postech.challenge_01.application.usecases.menu_item;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemUpdateRequestDTO;
import com.postech.challenge_01.application.mappers.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdateMenuItemUseCase implements UseCase<MenuItemUpdateRequestDTO, MenuItem> {
    private final IMenuItemGateway gateway;
    private final List<Rule<MenuItem>> rules;

    @SneakyThrows
    @Transactional
    @Override
    public MenuItem execute(MenuItemUpdateRequestDTO request) {
        var menuItem = MenuItemMapper.toMenuItem(request);
        var menuItemId = menuItem.getId();

        this.rules.forEach(rule -> rule.execute(menuItem));

        log.info("Atualizando item do menu com ID {}", request.id());
        var updatedMenuItem = this.gateway.update(menuItem, menuItemId);

        log.info("Atualizado item do menu {}", updatedMenuItem);
        return updatedMenuItem;
    }
}
