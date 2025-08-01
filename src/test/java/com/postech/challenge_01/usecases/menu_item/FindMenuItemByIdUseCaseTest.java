package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.application.usecases.menu_item.FindMenuItemByIdUseCase;
import com.postech.challenge_01.builder.menu_item.MenuItemBuilder;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class FindMenuItemByIdUseCaseTest {
    @Mock
    private IMenuItemGateway gateway;

    @InjectMocks
    private FindMenuItemByIdUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldFindMenuItemAndReturn() {
        // Arrange
        var menuItemId = 1L;
        var menuItem = MenuItemBuilder.oneMenuItem().build();

        when(this.gateway.findById(anyLong())).thenReturn(menuItem);

        // Act
        var response = this.useCase.execute(menuItemId);

        // Assert
        verify(this.gateway).findById(menuItemId);

        assertNotNull(response);
        assertEquals(menuItem.getId(), response.getId());
        assertEquals(menuItem.getMenuId(), response.getMenuId());
        assertEquals(menuItem.getName(), response.getName());
        assertEquals(menuItem.getDescription(), response.getDescription());
        assertEquals(menuItem.getPrice(), response.getPrice());
        assertEquals(menuItem.getDineInOnly(), response.getDineInOnly());
    }

    @Test
    void shouldNotFindMenuItemAndThrowError() {
        // Arrange
        var menuItemId = 1L;

        doThrow(ResourceNotFoundException.class).when(this.gateway).findById(anyLong());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> this.useCase.execute(menuItemId));

        verify(this.gateway).findById(menuItemId);
    }
}