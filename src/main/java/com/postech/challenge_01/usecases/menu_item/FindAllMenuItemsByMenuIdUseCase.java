package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.dtos.requests.menu_item.MenuItemsByMenuIdRequest;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.mappers.meu_item.MenuItemMapper;
import com.postech.challenge_01.repositories.menu_item.MenuItemRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindAllMenuItemsByMenuIdUseCase implements UseCase<MenuItemsByMenuIdRequest, List<MenuItemResponseDTO>> {
    private final MenuItemRepository repository;

    @Transactional
    @Override
    public List<MenuItemResponseDTO> execute(MenuItemsByMenuIdRequest request) {
        log.info("Listando itens do menu {}", request.menuId());
        var list = this.repository.findAllByMenuId(request.menuId(), request.size(), request.page());

        log.info("Itens do menu com ID {} retornados {}", request.menuId(), list);
        return MenuItemMapper.menuItemsToMenuItemResponseDTOList(list);
    }
}

