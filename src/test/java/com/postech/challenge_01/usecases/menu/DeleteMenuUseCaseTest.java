package com.postech.challenge_01.usecases.menu;

import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.repositories.menu.MenuRepository;
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

class DeleteMenuUseCaseTest {
    @Mock
    private MenuRepository menuRepository;

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

        when(this.menuRepository.delete(anyLong())).thenReturn(1);

        // Act
        this.useCase.execute(menuId);

        // Assert
        verify(this.menuRepository).delete(menuId);
    }

    @Test
    void shouldExecuteAndThrowsError() {
        // Arrange
        var menuId = 1L;

        when(this.menuRepository.delete(anyLong())).thenReturn(0);

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> this.useCase.execute(menuId));
        verify(this.menuRepository).delete(menuId);
    }
}