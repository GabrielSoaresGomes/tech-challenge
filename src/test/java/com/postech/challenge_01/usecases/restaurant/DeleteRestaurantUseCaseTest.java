package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.application.usecases.restaurant.DeleteRestaurantUseCase;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;

public class DeleteRestaurantUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private IRestaurantGateway restaurantGateway;

    @Mock
    private IAddressGateway addressGateway;

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
        Long restaurantId = 1L;

        deleteRestaurantUseCase.execute(restaurantId);

        verify(restaurantGateway, times(1)).delete(restaurantId);
    }
}
