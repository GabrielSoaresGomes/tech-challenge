package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.domains.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemsByMenuIdRequestDTO;
import com.postech.challenge_01.repositories.menu_item.MenuItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindAllMenuItemsByMenuIdUseCaseTest {
    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private FindAllMenuItemsByMenuIdUseCase useCase;

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
    void execute() {
        // Arrange
        var menuId = 1L;
        var request = new MenuItemsByMenuIdRequestDTO(menuId, 0, 10);
        var menuItem1 = new MenuItem(
                1L,
                menuId,
                "Nome do item 1",
                "Descrição do item 1",
                BigDecimal.valueOf(39.9),
                true,
                new byte[]{},
                "filename.png",
                "image/png"
        );
        var menuItem2 = new MenuItem(
                2L,
                menuId,
                "Nome do item 2",
                "Descrição do item 2",
                BigDecimal.valueOf(49.9),
                false,
                new byte[]{},
                "filename.png",
                "image/png"
        );
        var menuItems = List.of(menuItem1, menuItem2);

        when(this.menuItemRepository.findAllByMenuId(anyLong(), anyInt(), anyLong())).thenReturn(menuItems);

        // Act
        var result = useCase.execute(request);

        // Assert
        verify(this.menuItemRepository).findAllByMenuId(request.menuId(), request.size(), (long) request.page() * request.size());

        assertEquals(menuItems.size(), result.size());

        var resultMenu1 = result.getFirst();
        assertEquals(menuItem1.getId(), resultMenu1.id());
        assertEquals(menuItem1.getMenuId(), resultMenu1.menuId());
        assertEquals(menuItem1.getName(), resultMenu1.name());
        assertEquals(menuItem1.getDescription(), resultMenu1.description());
        assertEquals(menuItem1.getPrice(), resultMenu1.price());
        assertEquals(menuItem1.getDineInOnly(), resultMenu1.dineInOnly());

        var resultMenu2 = result.get(1);
        assertEquals(menuItem2.getId(), resultMenu2.id());
        assertEquals(menuItem2.getMenuId(), resultMenu2.menuId());
        assertEquals(menuItem2.getName(), resultMenu2.name());
        assertEquals(menuItem2.getDescription(), resultMenu2.description());
        assertEquals(menuItem2.getPrice(), resultMenu2.price());
        assertEquals(menuItem2.getDineInOnly(), resultMenu2.dineInOnly());
    }
}