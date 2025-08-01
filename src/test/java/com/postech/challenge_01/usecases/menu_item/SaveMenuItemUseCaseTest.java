package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.application.usecases.menu_item.SaveMenuItemUseCase;
import com.postech.challenge_01.application.usecases.rules.menu_item.ExistsMenuRule;
import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import com.postech.challenge_01.exceptions.MenuNotFoundException;
import com.postech.challenge_01.mappers.meu_item.MenuItemMapper;
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

class SaveMenuItemUseCaseTest {
    @Mock
    private IMenuItemGateway gateway;

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
    void shouldCreateAndSaveSuccessfully() throws IOException {
        // Arrange
        var menuItem = MenuItemMapper.toMenuItem(this.requestDTO);

        doNothing().when(this.existsMenuRule).execute(any(MenuItem.class));
        when(this.gateway.save(any(MenuItem.class))).thenReturn(menuItem);

        // Act
        var response = this.useCase.execute(this.requestDTO);

        // Assert
        verify(this.existsMenuRule).execute(any(MenuItem.class));
        verify(this.gateway).save(any(MenuItem.class));

        assertNotNull(response);
        assertEquals(this.requestDTO.menuId(), response.getMenuId());
        assertEquals(this.requestDTO.name(), response.getName());
        assertEquals(this.requestDTO.description(), response.getDescription());
        assertEquals(this.requestDTO.price(), response.getPrice());
        assertEquals(this.requestDTO.dineInOnly(), response.getDineInOnly());
    }

    @Test
    void shouldCreateAndFailRuleMeuExists() {
        // Arrange
        doThrow(MenuNotFoundException.class).when(this.existsMenuRule).execute(any(MenuItem.class));
        when(this.gateway.save(any(MenuItem.class))).thenReturn(null);

        // Act + Assert
        assertThrows(MenuNotFoundException.class, () -> this.useCase.execute(this.requestDTO));

        verify(this.existsMenuRule).execute(any(MenuItem.class));
        verify(this.gateway, never()).save(any(MenuItem.class));
    }
}