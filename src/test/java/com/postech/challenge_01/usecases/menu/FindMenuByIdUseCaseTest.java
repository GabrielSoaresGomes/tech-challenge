package com.postech.challenge_01.usecases.menu;

import com.postech.challenge_01.application.gateways.IMenuGateway;
import com.postech.challenge_01.application.usecases.menu.FindMenuByIdUseCase;
import com.postech.challenge_01.domain.Menu;
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

class FindMenuByIdUseCaseTest {
    @Mock
    private IMenuGateway gateway;

    @InjectMocks
    private FindMenuByIdUseCase useCase;

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
    void shouldFindMenuAndReturn() {
        // Arrange
        var menuId = 1L;
        var menu = new Menu(menuId, 1L, null);

        when(this.gateway.findById(anyLong())).thenReturn(menu);

        // Act
        var response = this.useCase.execute(menuId);

        // Assert
        verify(this.gateway).findById(menuId);

        assertNotNull(response);
        assertEquals(menu.getId(), response.getId());
        assertEquals(menu.getRestaurantId(), response.getRestaurantId());
    }

    @Test
    void shouldNotFindMenuAndThrowError() {
        // Arrange
        var menuId = 1L;

        doThrow(ResourceNotFoundException.class).when(this.gateway).findById(anyLong());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> this.useCase.execute(menuId));

        verify(this.gateway).findById(menuId);
    }
}