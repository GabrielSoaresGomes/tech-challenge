package com.postech.challenge_01.usecases.menu;

import com.postech.challenge_01.application.gateways.IMenuGateway;
import com.postech.challenge_01.application.usecases.menu.DeleteMenuUseCase;
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

class DeleteMenuUseCaseTest {
    @Mock
    private IMenuGateway gateway;

    @InjectMocks
    private DeleteMenuUseCase useCase;

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
    void shouldExecuteAndDeleteMenuSuccessfully() {
        // Arrange
        var menuId = 1L;

        doNothing().when(this.gateway).delete(anyLong());

        // Act
        this.useCase.execute(menuId);

        // Assert
        verify(this.gateway).delete(menuId);
    }

    @Test
    void shouldExecuteAndThrowError() {
        // Arrange
        var menuId = 1L;

        doThrow(ResourceNotFoundException.class).when(this.gateway).delete(anyLong());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> this.useCase.execute(menuId));
        verify(this.gateway).delete(menuId);
    }
}