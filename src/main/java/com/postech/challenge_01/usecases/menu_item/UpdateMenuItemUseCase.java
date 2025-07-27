package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.domains.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.exceptions.MenuItemNotFoundException;
import com.postech.challenge_01.mappers.meu_item.MenuItemMapper;
import com.postech.challenge_01.repositories.menu_item.MenuItemRepository;
import com.postech.challenge_01.usecases.UseCase;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdateMenuItemUseCase implements UseCase<MenuItemUpdateRequestDTO, MenuItemResponseDTO> {
    private final MenuItemRepository repository;
    private final List<Rule<MenuItem>> rules;

    @SneakyThrows
    @Transactional
    @Override
    public MenuItemResponseDTO execute(MenuItemUpdateRequestDTO request) {
        var menuItem = MenuItemMapper.menuItemUpdateRequestDTOToMenuItem(request);
        var menuItemId = menuItem.getId();

        this.rules.forEach(rule -> rule.execute(menuItem));

        log.info("Atualizar item do menu com ID {}", request.id());
        var updatedMenuItem = this.repository.update(menuItem, menuItemId)
                .flatMap(menuItemOp -> this.repository.findById(menuItemOp.getId()))
                .orElseThrow(() -> new MenuItemNotFoundException(menuItemId));

        return MenuItemMapper.menuItemToMenuItemResponseDTO(updatedMenuItem);
    }
}
