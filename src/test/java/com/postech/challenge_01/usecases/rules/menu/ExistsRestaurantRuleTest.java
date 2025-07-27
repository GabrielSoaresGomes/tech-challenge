package com.postech.challenge_01.usecases.rules.menu;

import com.postech.challenge_01.domains.Menu;
import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.exceptions.RestaurantNotFoundException;
import com.postech.challenge_01.repositories.restaurant.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExistsRestaurantRuleTest {
    @Mock
    private RestaurantRepository restaurantRepository;

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
                "Chinesa",
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
        when(this.restaurantRepository.findById(anyLong())).thenReturn(Optional.of(this.restaurant));

        // Assert
        assertDoesNotThrow(() -> this.rule.execute(this.menu));
        verify(this.restaurantRepository).findById(anyLong());
    }

    @Test
    void shouldNotExistRestaurant() {
        // Arrange
        when(this.restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RestaurantNotFoundException.class, () -> this.rule.execute(this.menu));
        verify(this.restaurantRepository).findById(anyLong());
    }
}