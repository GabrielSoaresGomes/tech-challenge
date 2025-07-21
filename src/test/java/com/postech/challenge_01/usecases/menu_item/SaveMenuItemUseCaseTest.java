package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.domains.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import com.postech.challenge_01.exceptions.MenuNotFoundException;
import com.postech.challenge_01.mappers.meu_item.MenuItemMapper;
import com.postech.challenge_01.repositories.menu_item.MenuItemRepository;
import com.postech.challenge_01.usecases.rules.menu_item.ExistsMenuRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SaveMenuItemUseCaseTest {
    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ExistsMenuRule existsMenuRule;

    @InjectMocks
    private SaveMenuItemUseCase useCase;

    private AutoCloseable closeable;
    private MenuItemRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(this.useCase, "rules", List.of(this.existsMenuRule));

        this.requestDTO = new MenuItemRequestDTO(
                1L,
                "Nome do item",
                "Descrição do item",
                true,
                new byte[]{}
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldCreateAndSaveSuccessfully() {
        // Arrange
        var menuItem = MenuItemMapper.menuItemRequestDTOToMenuItem(this.requestDTO);

        doNothing().when(this.existsMenuRule).execute(any(MenuItem.class));
        when(this.menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);

        // Act
        var response = this.useCase.execute(this.requestDTO);

        // Assert
        verify(this.existsMenuRule).execute(any(MenuItem.class));
        verify(this.menuItemRepository).save(any(MenuItem.class));

        assertNotNull(response);
        assertEquals(menuItem.getMenuId(), response.menuId());
        assertEquals(menuItem.getName(), response.name());
        assertEquals(menuItem.getDescription(), response.description());
        assertEquals(menuItem.getDineInOnly(), response.dineInOnly());
        assertEquals(menuItem.getPlatePhoto(), response.platePhoto());
    }

    @Test
    void shouldCreateAndFailRuleMeuExists() {
        // Arrange
        doThrow(MenuNotFoundException.class).when(this.existsMenuRule).execute(any(MenuItem.class));
        when(this.menuItemRepository.save(any(MenuItem.class))).thenReturn(null);

        // Act + Assert
        assertThrows(MenuNotFoundException.class, () -> this.useCase.execute(this.requestDTO));

        verify(this.existsMenuRule).execute(any(MenuItem.class));
        verify(this.menuItemRepository, never()).save(any(MenuItem.class));
    }
}