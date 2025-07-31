package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.builder.menu_item.MenuItemBuilder;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.infrastructure.data_sources.repositories.menu_item.MenuItemRepository;
import com.postech.challenge_01.application.usecases.menu_item.FindPlatePhotoMenuItemByIdUseCase;
import org.junit.jupiter.api.AfterEach;
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

class FindPlatePhotoMenuItemByIdUseCaseTest {
    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private FindPlatePhotoMenuItemByIdUseCase useCase;

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
    void shouldFindMenuItemAndReturnPlatePhoto() {
        // Arrange
        var menuItemId = 1L;
        var menuItem = MenuItemBuilder.oneMenuItem().build();

        when(this.menuItemRepository.findById(anyLong())).thenReturn(Optional.of(menuItem));

        // Act
        var response = this.useCase.execute(menuItemId);

        // Assert
        verify(this.menuItemRepository).findById(menuItemId);

        assertNotNull(response);
        assertEquals(menuItem.getPlatePhotoContent(), response.content());
        assertEquals(menuItem.getPlatePhotoOriginalFilename(), response.filename());
        assertEquals(menuItem.getPlatePhotoMimeType(), response.mimeType());
    }

    @Test
    void shouldNotFindMenuItemAndThrowError() {
        // Arrange
        var menuItemId = 1L;

        when(this.menuItemRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> this.useCase.execute(menuItemId));

        verify(this.menuItemRepository).findById(menuItemId);
    }
}