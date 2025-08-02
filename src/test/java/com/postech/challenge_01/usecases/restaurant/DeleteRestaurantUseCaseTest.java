package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.application.usecases.restaurant.DeleteRestaurantUseCase;
import com.postech.challenge_01.interface_adapter.gateways.AddressGateway;
import com.postech.challenge_01.interface_adapter.gateways.RestaurantGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DeleteRestaurantUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private RestaurantGateway restaurantGateway;

    @Mock
    private AddressGateway addressGateway;

    @InjectMocks
    private DeleteRestaurantUseCase deleteRestaurantUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        deleteRestaurantUseCase = new DeleteRestaurantUseCase(restaurantGateway, addressGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldExecuteAndDeleteRestaurantById() {
        // Arrange
        Long restaurantId = 1L;

        // Act
        deleteRestaurantUseCase.execute(restaurantId);

        // Assert
        verify(restaurantGateway, times(1)).delete(restaurantId);
    }
}
