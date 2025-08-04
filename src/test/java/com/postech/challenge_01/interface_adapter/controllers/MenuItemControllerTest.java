package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.menu_item.*;
import com.postech.challenge_01.builder.menu_item.MenuItemBuilder;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemUpdateRequestDTO;
import com.postech.challenge_01.interface_adapter.presenters.ImagePresenter;
import com.postech.challenge_01.interface_adapter.presenters.MenuItemPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MenuItemControllerTest {

    @Mock
    private FindAllMenuItemsUseCase findAllMenuItemsUseCase;
    @Mock
    private FindMenuItemByIdUseCase findMenuItemByIdUseCase;
    @Mock
    private SaveMenuItemUseCase saveMenuItemUseCase;
    @Mock
    private UpdateMenuItemUseCase updateMenuItemUseCase;
    @Mock
    private DeleteMenuItemUseCase deleteMenuItemUseCase;

    @InjectMocks
    private MenuItemController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new MenuItemController(
                saveMenuItemUseCase,
                findAllMenuItemsUseCase,
                findMenuItemByIdUseCase,
                updateMenuItemUseCase,
                deleteMenuItemUseCase
        );
    }

    @Test
    void shouldGetMenuItemList() {
        var pageable = PageRequest.of(0, 10);
        var entityList = List.of(MenuItemBuilder.oneMenuItem().build());
        var expectedList = MenuItemPresenter.menuItemsToMenuItemResponseDTOList(entityList);

        when(findAllMenuItemsUseCase.execute(pageable)).thenReturn(entityList);

        var result = controller.getMenuItemList(pageable);

        assertEquals(expectedList, result);
        verify(findAllMenuItemsUseCase, times(1)).execute(pageable);
    }

    @Test
    void shouldGetMenuItemById() {
        Long id = 1L;
        var entity = MenuItemBuilder.oneMenuItem().withId(id).build();
        var expected = MenuItemPresenter.menuItemToMenuItemResponseDTO(entity);

        when(findMenuItemByIdUseCase.execute(id)).thenReturn(entity);

        var result = controller.getMenuItem(id);

        assertEquals(expected, result);
        verify(findMenuItemByIdUseCase, times(1)).execute(id);
    }

    @Test
    void shouldGetMenuItemPlatePhoto() {
        Long id = 1L;
        var entity = MenuItemBuilder.oneMenuItem().withId(id).build();
        var expected = ImagePresenter.menuItemToImageDTO(entity);

        when(findMenuItemByIdUseCase.execute(id)).thenReturn(entity);

        var result = controller.getMenuItemPlatePhoto(id);

        assertEquals(expected, result);
        verify(findMenuItemByIdUseCase, times(1)).execute(id);
    }

    @Test
    void shouldSaveMenuItem() {
        MenuItemRequestDTO request = new MenuItemRequestDTO(
                1L, "Prato", "Delícia", new BigDecimal("25.00"), true, mock(MultipartFile.class)
        );
        var entity = MenuItemBuilder.oneMenuItem().build();
        var expected = MenuItemPresenter.menuItemToMenuItemResponseDTO(entity);

        when(saveMenuItemUseCase.execute(request)).thenReturn(entity);

        var result = controller.saveMenuItem(request);

        assertEquals(expected, result);
        verify(saveMenuItemUseCase, times(1)).execute(request);
    }

    @Test
    void shouldUpdateMenuItem() {
        MenuItemUpdateRequestDTO request = new MenuItemUpdateRequestDTO(
                1L, "Atualizado", "Desc", new BigDecimal("27.00"), false, mock(MultipartFile.class)
        );
        var entity = MenuItemBuilder.oneMenuItem().build();
        var expected = MenuItemPresenter.menuItemToMenuItemResponseDTO(entity);

        when(updateMenuItemUseCase.execute(request)).thenReturn(entity);

        var result = controller.updateMenuItem(request);

        assertEquals(expected, result);
        verify(updateMenuItemUseCase, times(1)).execute(request);
    }

    @Test
    void shouldDeleteMenuItem() {
        Long id = 1L;

        controller.deleteMenuItem(id);

        verify(deleteMenuItemUseCase, times(1)).execute(id);
    }
}