package com.postech.challenge_01.usecases.rules.menu_item;

import com.postech.challenge_01.builder.menu.MenuBuilder;
import com.postech.challenge_01.builder.menu_item.MenuItemBuilder;
import com.postech.challenge_01.domain.Menu;
import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.exceptions.MenuNotFoundException;
import com.postech.challenge_01.infrastructure.data_sources.repositories.menu.MenuRepository;
import com.postech.challenge_01.application.usecases.rules.menu_item.ExistsMenuRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ExistsMenuRuleTest {
    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private ExistsMenuRule rule;

    private AutoCloseable closeable;
    private Menu menu;
    private MenuItem menuItem;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);

        this.menu = MenuBuilder.oneMenu().build();
        this.menuItem = MenuItemBuilder.oneMenuItem()
                .withMenuId(this.menu.getId())
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldNotInformMenuId() {
        // Arrange
        this.menuItem = MenuItemBuilder.oneMenuItem()
                .withMenuId(null)
                .build();

        // Act + Assert
        assertDoesNotThrow(() -> this.rule.execute(this.menuItem));
        verify(this.menuRepository, never()).findById(anyLong());
    }

    @Test
    void shouldExistMenu() {
        // Arrange
        when(this.menuRepository.findById(anyLong())).thenReturn(Optional.of(this.menu));

        // Act + Assert
        assertDoesNotThrow(() -> this.rule.execute(this.menuItem));
        verify(this.menuRepository).findById(anyLong());
    }

    @Test
    void shouldNotExistMenu() {
        // Arrange
        when(this.menuRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(MenuNotFoundException.class, () -> this.rule.execute(this.menuItem));
        verify(this.menuRepository).findById(anyLong());
    }
}