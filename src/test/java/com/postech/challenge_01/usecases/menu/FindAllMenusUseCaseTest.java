package com.postech.challenge_01.usecases.menu;

import com.postech.challenge_01.domains.menu.Menu;
import com.postech.challenge_01.repositories.menu.MenuRepository;
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

class FindAllMenusUseCaseTest {
    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private FindAllMenusUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnListOfMenuResponseDTO() {
        // Arrange
        var pageable = PageRequest.of(0, 10);
        var menu1 = new Menu(1L, 1L, null);
        var menu2 = new Menu(2L, 2L, null);
        var menus = List.of(menu1, menu2);

        when(this.menuRepository.findAll(anyInt(), anyLong())).thenReturn(menus);

        // Act
        var result = useCase.execute(pageable);

        // Assert
        verify(this.menuRepository).findAll(pageable.getPageSize(), pageable.getPageNumber());

        assertEquals(menus.size(), result.size());

        assertEquals(menu1.getId(), result.get(0).id());
        assertEquals(menu1.getRestaurantId(), result.get(0).restaurantId());

        assertEquals(menu2.getId(), result.get(1).id());
        assertEquals(menu2.getRestaurantId(), result.get(1).restaurantId());
    }
}