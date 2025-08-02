package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.application.usecases.menu_item.UpdateMenuItemUseCase;
import com.postech.challenge_01.application.usecases.rules.menu_item.ExistsMenuRule;
import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemUpdateRequestDTO;
import com.postech.challenge_01.exceptions.MenuItemNotFoundException;
import com.postech.challenge_01.mappers.MenuItemMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateMenuItemUseCaseTest {
    @Mock
    private IMenuItemGateway gateway;

    @Mock
    private ExistsMenuRule existsMenuRule;

    @InjectMocks
    private UpdateMenuItemUseCase useCase;

    private AutoCloseable closeable;
    private MenuItemUpdateRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(this.useCase, "rules", List.of(this.existsMenuRule));

        this.requestDTO = new MenuItemUpdateRequestDTO(
                1L,
                "Nome do item",
                "Descrição do item",
                BigDecimal.valueOf(39.9),
                true,
                new MockMultipartFile("filename.png", "filename.png", "image.png", new byte[]{})
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldUpdateMenuItemSuccessfully() throws IOException {
        // Arrange
        var menuItem = MenuItemMapper.toMenuItem(this.requestDTO);
        var menuItemWithMenuId = new MenuItem(
                menuItem.getId(),
                2L,
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getDineInOnly(),
                menuItem.getPlatePhotoContent(),
                menuItem.getPlatePhotoOriginalFilename(),
                menuItem.getPlatePhotoMimeType()
        );

        doNothing().when(this.existsMenuRule).execute(any(MenuItem.class));
        when(this.gateway.update(any(MenuItem.class), anyLong())).thenReturn(menuItemWithMenuId);

        // Act
        var response = this.useCase.execute(this.requestDTO);

        // Assert
        verify(this.existsMenuRule, times(1)).execute(any(MenuItem.class));
        verify(this.gateway, times(1)).update(any(MenuItem.class), anyLong());

        assertNotNull(response);
        assertEquals(menuItemWithMenuId.getId(), response.getId());
        assertEquals(menuItemWithMenuId.getMenuId(), response.getMenuId());
        assertEquals(menuItemWithMenuId.getName(), response.getName());
        assertEquals(menuItemWithMenuId.getDescription(), response.getDescription());
        assertEquals(menuItemWithMenuId.getPrice(), response.getPrice());
        assertEquals(menuItemWithMenuId.getDineInOnly(), response.getDineInOnly());
    }

    @Test
    void shouldTryUpdateMenuItemAndThrowError() {
        // Arrange
        doNothing().when(this.existsMenuRule).execute(any(MenuItem.class));
        doThrow(MenuItemNotFoundException.class).when(this.gateway).update(any(MenuItem.class), anyLong());

        // Act + Assert
        assertThrows(MenuItemNotFoundException.class, () -> this.useCase.execute(this.requestDTO));

        verify(this.existsMenuRule, times(1)).execute(any(MenuItem.class));
        verify(this.gateway, times(1)).update(any(MenuItem.class), anyLong());
    }
}