package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantUpdateRequestDTOBuilder;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateRequestDTO;
import com.postech.challenge_01.application.usecases.restaurant.UpdateRestaurantUseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UpdateRestaurantUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private IRestaurantGateway restaurantGateway;

    @Mock
    private Rule<Restaurant> ruleMock;

    @InjectMocks
    private UpdateRestaurantUseCase updateRestaurantUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        updateRestaurantUseCase = new UpdateRestaurantUseCase(restaurantGateway, List.of(ruleMock));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldExecuteAndUpdateRestaurantSuccessfully() {
        Long id = 1L;
        LocalDateTime lastModifiedDateTime = LocalDateTime.now();

        RestaurantUpdateRequestDTO requestDTO = RestaurantUpdateRequestDTOBuilder
                .oneRestaurantUpdateRequestDTO().build();

        Restaurant updatedRestaurant = RestaurantBuilder
                .oneRestaurant().withId(id).withLastModifiedDateTime(lastModifiedDateTime).build();

        Restaurant expectedResponse = RestaurantBuilder
                .oneRestaurant().withId(id).withLastModifiedDateTime(lastModifiedDateTime).build();

        when(restaurantGateway.update(any(Restaurant.class), anyLong())).thenReturn(updatedRestaurant);

        Restaurant response = updateRestaurantUseCase.execute(requestDTO);

        verify(restaurantGateway, times(1)).update(any(Restaurant.class), eq(id));
        verify(ruleMock).execute(any(Restaurant.class));

        assertThat(response).isEqualTo(expectedResponse);
        assertThat(response.getId()).isEqualTo(id);
    }
}
