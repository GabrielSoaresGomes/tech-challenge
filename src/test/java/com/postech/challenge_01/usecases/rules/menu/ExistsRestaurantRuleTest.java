package com.postech.challenge_01.usecases.rules.menu;

import com.postech.challenge_01.domain.Menu;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;
import com.postech.challenge_01.exceptions.RestaurantNotFoundException;
import com.postech.challenge_01.application.usecases.rules.menu.ExistsRestaurantRule;
import com.postech.challenge_01.interface_adapter.gateways.RestaurantGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExistsRestaurantRuleTest {
    @Mock
    private RestaurantGateway restaurantGateway;

    @InjectMocks
    private ExistsRestaurantRule rule;

    private AutoCloseable closeable;
    private Restaurant restaurant;
    private Menu menu;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);

        this.restaurant = new Restaurant(
                1L,
                1L,
                "Restaurante de Teste",
                RestaurantGenreEnum.CHINESE,
                LocalTime.NOON,
                LocalTime.MIDNIGHT
        );
        this.menu = new Menu(
                this.restaurant.getId()
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldExistRestaurant() {
        // Arrange
        when(this.restaurantGateway.findById(anyLong())).thenReturn(this.restaurant);

        // Assert
        assertDoesNotThrow(() -> this.rule.execute(this.menu));
        verify(this.restaurantGateway).findById(anyLong());
    }
}