package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.mappers.meu_item.MenuItemMapper;
import com.postech.challenge_01.repositories.menu_item.MenuItemRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindMenuItemByIdUseCase implements UseCase<Long, MenuItemResponseDTO> {
    private final MenuItemRepository repository;

    @Override
    public MenuItemResponseDTO execute(Long id) {
        log.info("Buscando item de menu com ID {}", id);
        var menuItem = this.repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item de menu não encontrado para o id %d".formatted(id)));

        log.info("Item de menu encontrado {}", id);
        return MenuItemMapper.menuItemToMenuItemResponseDTO(menuItem);
    }
}
