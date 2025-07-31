package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.infrastructure.data_sources.repositories.menu_item.MenuItemRepository;
import com.postech.challenge_01.application.usecases.menu_item.DeleteMenuItemUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteMenuItemUseCaseTest {
    @Mock
    private MenuItemRepository menuItemRepository;

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

        when(this.menuItemRepository.delete(anyLong())).thenReturn(1);

        // Act
        this.useCase.execute(menuItemId);

        // Assert
        verify(this.menuItemRepository).delete(menuItemId);
    }

    @Test
    void shouldExecuteAndThrowError() {
        // Arrange
        var menuItemId = 1L;

        when(this.menuItemRepository.delete(anyLong())).thenReturn(0);

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> this.useCase.execute(menuItemId));
        verify(this.menuItemRepository).delete(menuItemId);
    }
}