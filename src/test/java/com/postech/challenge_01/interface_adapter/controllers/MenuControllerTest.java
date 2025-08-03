package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.menu.*;
import com.postech.challenge_01.application.usecases.menu_item.FindAllMenuItemsByMenuIdUseCase;
import com.postech.challenge_01.builder.menu.MenuBuilder;
import com.postech.challenge_01.builder.menu_item.MenuItemBuilder;
import com.postech.challenge_01.dtos.requests.menu.MenuRequestDTO;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemsByMenuIdRequestDTO;
import com.postech.challenge_01.interface_adapter.presenters.MenuItemPresenter;
import com.postech.challenge_01.interface_adapter.presenters.MenuPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MenuControllerTest {

    @Mock
    private FindAllMenusUseCase findAllMenusUseCase;
    @Mock
    private FindMenuByIdUseCase findMenuByIdUseCase;
    @Mock
    private FindAllMenuItemsByMenuIdUseCase findMenuItemsByMenuIdUseCase;
    @Mock
    private SaveMenuUseCase saveMenuUseCase;
    @Mock
    private DeleteMenuUseCase deleteMenuUseCase;

    @InjectMocks
    private MenuController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new MenuController(
                findAllMenusUseCase,
                findMenuItemsByMenuIdUseCase,
                findMenuByIdUseCase,
                saveMenuUseCase,
                deleteMenuUseCase
        );
    }

    @Test
    void shouldGetMenuList() {
        var pageable = PageRequest.of(0, 10);
        var entityList = List.of(MenuBuilder.oneMenu().build());
        var expectedList = MenuPresenter.menusToMenuResponseDTOList(entityList);

        when(findAllMenusUseCase.execute(pageable)).thenReturn(entityList);

        var result = controller.getMenuList(pageable);

        assertEquals(expectedList, result);
        verify(findAllMenusUseCase, times(1)).execute(pageable);
    }

    @Test
    void shouldGetMenuById() {
        Long id = 1L;
        var entity = MenuBuilder.oneMenu().withId(id).build();
        var expected = MenuPresenter.menuToMenuResponseDTO(entity);

        when(findMenuByIdUseCase.execute(id)).thenReturn(entity);

        var result = controller.getMenu(id);

        assertEquals(expected, result);
        verify(findMenuByIdUseCase, times(1)).execute(id);
    }

    @Test
    void shouldGetMenuItemsByMenuId() {
        Long id = 1L;
        var pageable = PageRequest.of(0, 10);
        var entityList = List.of(MenuItemBuilder.oneMenuItem().build());
        var expectedList = MenuItemPresenter.menuItemsToMenuItemResponseDTOList(entityList);
        var menuItemsByMenuIdRequestDTO = new MenuItemsByMenuIdRequestDTO(id, pageable);

        when(findMenuItemsByMenuIdUseCase.execute(menuItemsByMenuIdRequestDTO)).thenReturn(entityList);

        var result = controller.getMenuItemListByMenuId(id, pageable);

        assertEquals(expectedList, result);
        verify(findMenuItemsByMenuIdUseCase, times(1)).execute(menuItemsByMenuIdRequestDTO);
    }

    @Test
    void shouldSaveMenu() {
        var request = new MenuRequestDTO( 1L);
        var entity = MenuBuilder.oneMenu().build();
        var expected = MenuPresenter.menuToMenuResponseDTO(entity);

        when(saveMenuUseCase.execute(request)).thenReturn(entity);

        var result = controller.saveMenu(request);

        assertEquals(expected, result);
        verify(saveMenuUseCase, times(1)).execute(request);
    }

    @Test
    void shouldDeleteMenu() {
        Long id = 1L;

        controller.deleteMenu(id);

        verify(deleteMenuUseCase, times(1)).execute(id);
    }
}
