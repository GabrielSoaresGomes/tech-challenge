package com.postech.challenge_01.usecases.menu;

import com.postech.challenge_01.application.gateways.IMenuGateway;
import com.postech.challenge_01.application.usecases.menu.FindAllMenusUseCase;
import com.postech.challenge_01.domain.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindAllMenusUseCaseTest {
    @Mock
    private IMenuGateway gateway;

    @InjectMocks
    private FindAllMenusUseCase useCase;

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
    void shouldReturnListOfMenuResponseDTO() {
        // Arrange
        var pageable = PageRequest.of(0, 10);
        var menu1 = new Menu(1L, 1L, null);
        var menu2 = new Menu(2L, 2L, null);
        var menus = List.of(menu1, menu2);

        when(this.gateway.findAll(any(Pageable.class))).thenReturn(menus);

        // Act
        var result = useCase.execute(pageable);

        // Assert
        verify(this.gateway).findAll(pageable);

        assertEquals(menus.size(), result.size());

        assertEquals(menu1.getId(), result.get(0).getId());
        assertEquals(menu1.getRestaurantId(), result.get(0).getRestaurantId());

        assertEquals(menu2.getId(), result.get(1).getId());
        assertEquals(menu2.getRestaurantId(), result.get(1).getRestaurantId());
    }
}