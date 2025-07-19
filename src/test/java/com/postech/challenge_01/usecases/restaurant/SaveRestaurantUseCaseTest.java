package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.repositories.RestaurantRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class SaveRestaurantUseCaseTest {
    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private Rule<Restaurant> ruleMock;

    @InjectMocks
    private SaveRestaurantUseCase saveRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saveRestaurantUseCase = new SaveRestaurantUseCase(restaurantRepository, List.of(ruleMock));
    }

    @Test
    void shouldExecuteAndSaveRestaurantSuccessfully() {
        // Arrange
        Long id = 1L;
        Long ownerId = 1L;
        Long restaurantId = 1L;
        String name = "Restaurante Teste";
        String type = "Tipo Teste";
        String startTime = "08:00:00";
        String endTime = "23:00:00";

        fail("Código de teste não implementado!");
    }

    @Test
    void shouldExecuteAndReturnError() {
        fail("Código de teste não implementado!");
    }
}
