package com.postech.challenge_01.application.usecases.menu_item;

import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.mappers.meu_item.MenuItemMapper;
import com.postech.challenge_01.infrastructure.data_sources.repositories.menu_item.MenuItemRepository;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SaveMenuItemUseCase implements UseCase<MenuItemRequestDTO, MenuItemResponseDTO> {
    private final MenuItemRepository repository;
    private final List<Rule<MenuItem>> rules;

    @SneakyThrows
    @Transactional
    @Override
    public MenuItemResponseDTO execute(MenuItemRequestDTO request) {
        var menuItem = MenuItemMapper.menuItemRequestDTOToMenuItem(request);

        log.info("Validando regras do item do menu {}", menuItem);
        this.rules.forEach(rule -> rule.execute(menuItem));

        log.info("Criando novo item do menu {}", menuItem);
        var savedMenuItem = this.repository.save(menuItem);

        log.info("Item do menu criado {}", savedMenuItem);
        return MenuItemMapper.menuItemToMenuItemResponseDTO(savedMenuItem);
    }
}
