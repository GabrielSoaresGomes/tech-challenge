package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.application.usecases.restaurant.FindRestaurantByIdUseCase;
import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class FindRestaurantByIdUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private IRestaurantGateway restaurantGateway;

    @InjectMocks
    private FindRestaurantByIdUseCase findRestaurantByIdUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        findRestaurantByIdUseCase = new FindRestaurantByIdUseCase(restaurantGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldExecuteAndReturnRestaurantById() {
        Long restaurantId = 1L;
        LocalDateTime lastModifiedDateTime = LocalDateTime.now();

        Restaurant expectedResponse = RestaurantBuilder
                .oneRestaurant().withId(restaurantId).withLastModifiedDateTime(lastModifiedDateTime).build();

        Restaurant restaurant = RestaurantBuilder
                .oneRestaurant().withId(restaurantId).withLastModifiedDateTime(lastModifiedDateTime).build();

        when(restaurantGateway.findById(anyLong())).thenReturn(restaurant);

        Restaurant response = findRestaurantByIdUseCase.execute(restaurantId);

        verify(restaurantGateway, times(1)).findById(restaurantId);
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(expectedResponse.getId());
        assertThat(response.getName()).isEqualTo(expectedResponse.getName());
        assertThat(response.getType()).isEqualTo(expectedResponse.getType());
        assertThat(response.getOwnerId()).isEqualTo(expectedResponse.getOwnerId());
        assertThat(response.getAddressId()).isEqualTo(expectedResponse.getAddressId());
        assertThat(response.getStartTime()).isEqualTo(expectedResponse.getStartTime());
        assertThat(response.getEndTime()).isEqualTo(expectedResponse.getEndTime());
        assertThat(response).isEqualTo(expectedResponse);
        assertThat(response).isInstanceOf(Restaurant.class);
    }
}
