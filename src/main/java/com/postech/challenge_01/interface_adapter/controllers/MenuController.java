package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.menu.DeleteMenuUseCase;
import com.postech.challenge_01.application.usecases.menu.FindAllMenusUseCase;
import com.postech.challenge_01.application.usecases.menu.FindMenuByIdUseCase;
import com.postech.challenge_01.application.usecases.menu.SaveMenuUseCase;
import com.postech.challenge_01.application.usecases.menu_item.FindAllMenuItemsByMenuIdUseCase;
import com.postech.challenge_01.dtos.requests.menu.MenuRequestDTO;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemsByMenuIdRequestDTO;
import com.postech.challenge_01.dtos.responses.menu.MenuResponseDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.interface_adapter.presenters.MenuItemPresenter;
import com.postech.challenge_01.interface_adapter.presenters.MenuPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuController {
    private final FindAllMenusUseCase findAllMenusUseCase;
    private final FindAllMenuItemsByMenuIdUseCase findAllMenuItemsByMenuIdUseCase;
    private final FindMenuByIdUseCase findMenuByIdUseCase;
    private final SaveMenuUseCase saveMenuUseCase;
    private final DeleteMenuUseCase deleteMenuUseCase;

    public MenuResponseDTO getMenu(Long id) {
        var menu = this.findMenuByIdUseCase.execute(id);

        return MenuPresenter.menuToMenuResponseDTO(menu);
    }

    public List<MenuResponseDTO> getMenuList(Pageable pageable) {
        var list = this.findAllMenusUseCase.execute(pageable);

        return MenuPresenter.menusToMenuResponseDTOList(list);
    }

    public MenuResponseDTO saveMenu(MenuRequestDTO request) {
        var savedMenu = this.saveMenuUseCase.execute(request);

        return MenuPresenter.menuToMenuResponseDTO(savedMenu);
    }

    public void deleteMenu(Long id) {
        this.deleteMenuUseCase.execute(id);
    }

    public List<MenuItemResponseDTO> getMenuItemListByMenuId(Long id, Pageable pageable) {
        var request = new MenuItemsByMenuIdRequestDTO(id, pageable);
        var list = this.findAllMenuItemsByMenuIdUseCase.execute(request);

        return MenuItemPresenter.menuItemsToMenuItemResponseDTOList(list);
    }
}
