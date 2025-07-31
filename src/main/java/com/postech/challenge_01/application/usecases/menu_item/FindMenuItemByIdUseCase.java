package com.postech.challenge_01.application.usecases.menu_item;

import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.mappers.meu_item.MenuItemMapper;
import com.postech.challenge_01.infrastructure.data_sources.repositories.menu_item.MenuItemRepository;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindMenuItemByIdUseCase implements UseCase<Long, MenuItemResponseDTO> {
    private final MenuItemRepository repository;

    @Transactional
    @Override
    public MenuItemResponseDTO execute(Long id) {
        log.info("Buscando item do menu com ID {}", id);
        var menuItem = this.repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item do menu não encontrado para o id %d".formatted(id)));

        log.info("Item do menu encontrado {}", id);
        return MenuItemMapper.menuItemToMenuItemResponseDTO(menuItem);
    }
}
