package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.application.usecases.menu_item.FindAllMenuItemsByMenuIdUseCase;
import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemsByMenuIdRequestDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindAllMenuItemsByMenuIdUseCaseTest {
    @Mock
    private IMenuItemGateway gateway;

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
        var pageRequest = PageRequest.of(0, 10);
        var request = new MenuItemsByMenuIdRequestDTO(menuId, pageRequest);
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

        when(this.gateway.findAllByMenuId(anyLong(), any(Pageable.class))).thenReturn(menuItems);

        // Act
        var result = useCase.execute(request);

        // Assert
        verify(this.gateway).findAllByMenuId(request.menuId(), pageRequest);

        assertEquals(menuItems.size(), result.size());

        var resultMenu1 = result.getFirst();
        assertEquals(menuItem1.getId(), resultMenu1.getId());
        assertEquals(menuItem1.getMenuId(), resultMenu1.getMenuId());
        assertEquals(menuItem1.getName(), resultMenu1.getName());
        assertEquals(menuItem1.getDescription(), resultMenu1.getDescription());
        assertEquals(menuItem1.getPrice(), resultMenu1.getPrice());
        assertEquals(menuItem1.getDineInOnly(), resultMenu1.getDineInOnly());

        var resultMenu2 = result.get(1);
        assertEquals(menuItem2.getId(), resultMenu2.getId());
        assertEquals(menuItem2.getMenuId(), resultMenu2.getMenuId());
        assertEquals(menuItem2.getName(), resultMenu2.getName());
        assertEquals(menuItem2.getDescription(), resultMenu2.getDescription());
        assertEquals(menuItem2.getPrice(), resultMenu2.getPrice());
        assertEquals(menuItem2.getDineInOnly(), resultMenu2.getDineInOnly());
    }
}