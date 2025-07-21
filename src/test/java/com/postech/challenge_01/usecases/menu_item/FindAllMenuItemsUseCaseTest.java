package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.domains.menu_item.MenuItem;
import com.postech.challenge_01.repositories.menu_item.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindAllMenuItemsUseCaseTest {
    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private FindAllMenuItemsUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute() {
        // Arrange
        var pageable = PageRequest.of(0, 10);
        var menuItem1 = new MenuItem(
                1L,
                2L,
                "Nome do item 1",
                "Descrição do item 1",
                true,
                new byte[]{}
        );
        var menuItem2 = new MenuItem(
                2L,
                2L,
                "Nome do item 2",
                "Descrição do item 2",
                false,
                new byte[]{}
        );
        var menuItems = List.of(menuItem1, menuItem2);

        when(this.menuItemRepository.findAll(anyInt(), anyLong())).thenReturn(menuItems);

        // Act
        var result = useCase.execute(pageable);

        // Assert
        verify(this.menuItemRepository).findAll(pageable.getPageSize(), pageable.getPageNumber());

        assertEquals(menuItems.size(), result.size());

        assertEquals(menuItem1.getId(), result.get(0).id());
        assertEquals(menuItem1.getMenuId(), result.get(0).menuId());
        assertEquals(menuItem1.getName(), result.get(0).name());
        assertEquals(menuItem1.getDescription(), result.get(0).description());
        assertEquals(menuItem1.getDineInOnly(), result.get(0).dineInOnly());
        assertEquals(menuItem1.getPlatePhoto(), result.get(0).platePhoto());

        assertEquals(menuItem2.getId(), result.get(1).id());
        assertEquals(menuItem2.getMenuId(), result.get(1).menuId());
        assertEquals(menuItem2.getName(), result.get(1).name());
        assertEquals(menuItem2.getDescription(), result.get(1).description());
        assertEquals(menuItem2.getDineInOnly(), result.get(1).dineInOnly());
        assertEquals(menuItem2.getPlatePhoto(), result.get(1).platePhoto());
    }
}