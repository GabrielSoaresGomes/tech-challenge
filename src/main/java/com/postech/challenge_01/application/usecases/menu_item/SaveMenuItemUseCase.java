package com.postech.challenge_01.application.usecases.menu_item;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import com.postech.challenge_01.mappers.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SaveMenuItemUseCase implements UseCase<MenuItemRequestDTO, MenuItem> {
    private final IMenuItemGateway gateway;
    private final List<Rule<MenuItem>> rules;

    @SneakyThrows
    @Transactional
    @Override
    public MenuItem execute(MenuItemRequestDTO request) {
        var menuItem = MenuItemMapper.toMenuItem(request);

        log.info("Validando regras do item do menu {}", menuItem);
        this.rules.forEach(rule -> rule.execute(menuItem));

        log.info("Criando novo item do menu {}", menuItem);
        var savedMenuItem = this.gateway.save(menuItem);

        log.info("Item do menu criado {}", savedMenuItem);
        return savedMenuItem;
    }
}
