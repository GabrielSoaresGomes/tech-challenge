package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.domains.menu_item.MenuItem;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.repositories.menu_item.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindMenuItemByIdUseCaseTest {
    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private FindMenuItemByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindMenuItemAndReturn() {
        // Arrange
        var menuItemId = 1L;
        var menuItem = new MenuItem(
                menuItemId,
                1L,
                "Nome do item",
                "Descrição do item",
                true,
                new byte[]{}
        );

        when(this.menuItemRepository.findById(anyLong())).thenReturn(Optional.of(menuItem));

        // Act
        var response = this.useCase.execute(menuItemId);

        // Assert
        verify(this.menuItemRepository).findById(menuItemId);

        assertNotNull(response);
        assertEquals(menuItem.getId(), response.id());
        assertEquals(menuItem.getMenuId(), response.menuId());
        assertEquals(menuItem.getName(), response.name());
        assertEquals(menuItem.getDescription(), response.description());
        assertEquals(menuItem.getDineInOnly(), response.dineInOnly());
        assertEquals(menuItem.getPlatePhoto(), response.platePhoto());
    }

    @Test
    void shouldNotFindMenuItemAndThrowError() {
        // Arrange
        var menuItemId = 1L;

        when(this.menuItemRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> this.useCase.execute(menuItemId));

        verify(this.menuItemRepository).findById(menuItemId);
    }
}