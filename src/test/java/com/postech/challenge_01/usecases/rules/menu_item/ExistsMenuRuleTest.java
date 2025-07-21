package com.postech.challenge_01.usecases.rules.menu_item;

import com.postech.challenge_01.domains.menu.Menu;
import com.postech.challenge_01.domains.menu_item.MenuItem;
import com.postech.challenge_01.exceptions.MenuNotFoundException;
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

class ExistsMenuRuleTest {
    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private ExistsMenuRule rule;

    private static final Long MENU_ID = 1L;

    private Menu menu;
    private MenuItem menuItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        this.menu = new Menu(MENU_ID, null, null);
        this.menuItem = new MenuItem(
                1L,
                "Nome do item",
                "Descrição do item",
                true,
                new byte[]{}
        );
    }

    @Test
    void shouldExistMenu() {
        // Arrange
        when(this.menuRepository.findById(anyLong())).thenReturn(Optional.of(menu));

        // Assert
        assertDoesNotThrow(() -> this.rule.execute(menuItem));
        verify(this.menuRepository).findById(anyLong());
    }

    @Test
    void shouldNotExistMenu() {
        // Arrange
        when(this.menuRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Assert
        assertThrows(MenuNotFoundException.class, () -> this.rule.execute(menuItem));
        verify(this.menuRepository).findById(anyLong());
    }
}