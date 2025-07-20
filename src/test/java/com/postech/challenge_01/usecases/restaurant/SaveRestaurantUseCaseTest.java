package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantRequestDTOBuilder;
import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.repositories.restaurant.RestaurantRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        RestaurantRequestDTO requestDTO = RestaurantRequestDTOBuilder
                .oneRestaurantRequestDTO()
                .build();

        Restaurant savedRestaurant = RestaurantBuilder
                .oneRestaurant()
                .withId(id)
                .build();

        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(savedRestaurant);

        // Act
        RestaurantResponseDTO response = saveRestaurantUseCase.execute(requestDTO);

        // Assert
        verify(ruleMock).execute(any(Restaurant.class));
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.ownerId()).isEqualTo(requestDTO.ownerId());
        assertThat(response.addressId()).isEqualTo(requestDTO.addressId());
        assertThat(response.name()).isEqualTo(requestDTO.name());
        assertThat(response.type()).isEqualTo(requestDTO.type());
        assertThat(response.startTime()).isEqualTo(requestDTO.startTime());
        assertThat(response.endTime()).isEqualTo(requestDTO.endTime());
    }

    @Test
    void shouldThrowInvalidRule() {
        // Arrange
        RestaurantRequestDTO requestDTO = RestaurantRequestDTOBuilder
                .oneRestaurantRequestDTO()
                .build();

        doThrow(new RuntimeException("Rule")).when(ruleMock).execute(any(Restaurant.class));

        // Assert
        assertThrows(RuntimeException.class, () -> saveRestaurantUseCase.execute(requestDTO));
        verify(restaurantRepository, never()).save(any(Restaurant.class));
    }
}
