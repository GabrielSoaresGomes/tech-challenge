package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantResponseDTOBuilder;
import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.repositories.restaurant.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class FindRestaurantByIdUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private FindRestaurantByIdUseCase findRestaurantByIdUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        findRestaurantByIdUseCase = new FindRestaurantByIdUseCase(restaurantRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldExecuteAndReturnRestaurantById() {
        // Arrange
        Long restaurantId = 1L;
        RestaurantResponseDTO expectedResponse = RestaurantResponseDTOBuilder
                .oneRestaurantResponseDTO().withId(restaurantId).build();

        Restaurant restaurant = RestaurantBuilder
                .oneRestaurant().withId(restaurantId).build();

        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));

        // Act
        RestaurantResponseDTO response = findRestaurantByIdUseCase.execute(restaurantId);

        // Assert
        verify(restaurantRepository, times(1)).findById(restaurantId);
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(expectedResponse.id());
        assertThat(response.name()).isEqualTo(expectedResponse.name());
        assertThat(response.type()).isEqualTo(expectedResponse.type());
        assertThat(response.ownerId()).isEqualTo(expectedResponse.ownerId());
        assertThat(response.addressId()).isEqualTo(expectedResponse.addressId());
        assertThat(response.startTime()).isEqualTo(expectedResponse.startTime());
        assertThat(response.endTime()).isEqualTo(expectedResponse.endTime());
        assertThat(response).isEqualTo(expectedResponse);
        assertThat(response).isInstanceOf(RestaurantResponseDTO.class);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenRestaurantNotFound() {
        // Arrange
        Long restaurantId = 1L;
        when(restaurantRepository.findById(restaurantId)).thenReturn(java.util.Optional.empty());

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> findRestaurantByIdUseCase.execute(restaurantId));

        verify(restaurantRepository, times(1)).findById(restaurantId);
    }
}
