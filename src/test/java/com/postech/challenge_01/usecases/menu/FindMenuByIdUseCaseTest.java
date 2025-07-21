package com.postech.challenge_01.usecases.menu;

import com.postech.challenge_01.domains.menu.Menu;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.repositories.menu.MenuRepository;
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

class FindMenuByIdUseCaseTest {
    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private FindMenuByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindMenuAndReturn() {
        // Arrange
        var menuId = 1L;
        var menu = new Menu(menuId, 1L, null);

        when(this.menuRepository.findById(anyLong())).thenReturn(Optional.of(menu));

        // Act
        var response = this.useCase.execute(menuId);

        // Assert
        verify(this.menuRepository).findById(menuId);

        assertNotNull(response);
        assertEquals(menu.getId(), response.id());
        assertEquals(menu.getRestaurantId(), response.restaurantId());
    }

    @Test
    void shouldNotFindMenuAndThrowError() {
        // Arrange
        var menuId = 1L;

        when(this.menuRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> this.useCase.execute(menuId));

        verify(this.menuRepository).findById(menuId);
    }
}