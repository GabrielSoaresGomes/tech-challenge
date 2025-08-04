package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.menu_item.*;
import com.postech.challenge_01.dtos.common.ImageDTO;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.interface_adapter.presenters.ImagePresenter;
import com.postech.challenge_01.interface_adapter.presenters.MenuItemPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuItemController {
    private final SaveMenuItemUseCase saveMenuItemUseCase;
    private final FindAllMenuItemsUseCase findAllMenuItemsUseCase;
    private final FindMenuItemByIdUseCase findMenuItemByIdUseCase;
    private final UpdateMenuItemUseCase updateMenuItemUseCase;
    private final DeleteMenuItemUseCase deleteMenuItemUseCase;

    public MenuItemResponseDTO getMenuItem(Long id) {
        var menuItem = this.findMenuItemByIdUseCase.execute(id);

        return MenuItemPresenter.menuItemToMenuItemResponseDTO(menuItem);
    }

    public ImageDTO getMenuItemPlatePhoto(Long id) {
        var menuItem = this.findMenuItemByIdUseCase.execute(id);

        return ImagePresenter.menuItemToImageDTO(menuItem);
    }

    public List<MenuItemResponseDTO> getMenuItemList(Pageable pageable) {
        var list = this.findAllMenuItemsUseCase.execute(pageable);

        return MenuItemPresenter.menuItemsToMenuItemResponseDTOList(list);
    }

    public MenuItemResponseDTO saveMenuItem(MenuItemRequestDTO request) {
        var savedMenuItem = this.saveMenuItemUseCase.execute(request);

        return MenuItemPresenter.menuItemToMenuItemResponseDTO(savedMenuItem);
    }

    public MenuItemResponseDTO updateMenuItem(MenuItemUpdateRequestDTO request) {
        var updatedMenuItem = this.updateMenuItemUseCase.execute(request);

        return MenuItemPresenter.menuItemToMenuItemResponseDTO(updatedMenuItem);
    }

    public void deleteMenuItem(Long id) {
        this.deleteMenuItemUseCase.execute(id);
    }
}
