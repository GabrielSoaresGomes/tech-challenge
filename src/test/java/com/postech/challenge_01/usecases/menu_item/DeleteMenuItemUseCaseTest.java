package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.application.usecases.menu_item.DeleteMenuItemUseCase;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DeleteMenuItemUseCaseTest {
    @Mock
    private IMenuItemGateway gateway;

    @InjectMocks
    private DeleteMenuItemUseCase useCase;

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
    void shouldExecuteAndDeleteMenuItemSuccessfully() {
        // Arrange
        var menuItemId = 1L;

        doNothing().when(this.gateway).delete(anyLong());

        // Act
        this.useCase.execute(menuItemId);

        // Assert
        verify(this.gateway).delete(menuItemId);
    }

    @Test
    void shouldExecuteAndThrowError() {
        // Arrange
        var menuItemId = 1L;

        doThrow(ResourceNotFoundException.class).when(this.gateway).delete(anyLong());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> this.useCase.execute(menuItemId));
        verify(this.gateway).delete(menuItemId);
    }
}