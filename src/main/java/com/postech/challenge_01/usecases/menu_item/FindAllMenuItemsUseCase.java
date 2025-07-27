package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.mappers.meu_item.MenuItemMapper;
import com.postech.challenge_01.repositories.menu_item.MenuItemRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindAllMenuItemsUseCase implements UseCase<Pageable, List<MenuItemResponseDTO>> {
    private final MenuItemRepository repository;

    @Transactional
    @Override
    public List<MenuItemResponseDTO> execute(Pageable pageable) {
        log.info("Listando itens de menu");
        var list = this.repository.findAll(pageable.getPageSize(), pageable.getPageNumber());

        log.info("Itens de menu retornados {}", list);
        return MenuItemMapper.menuItemsToMenuItemResponseDTOList(list);
    }
}

