package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.builder.restaurant.FindAllRestaurantsRequestDTOBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantResponseDTOBuilder;
import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.dtos.requests.restaurant.FindAllRestaurantsRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.repositories.restaurant.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FindAllRestaurantsUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private FindAllRestaurantsUseCase findAllRestaurantsUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        findAllRestaurantsUseCase = new FindAllRestaurantsUseCase(restaurantRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldExecuteAndReturnAllRestaurants() {
        // Arrange
        FindAllRestaurantsRequestDTO requestDTO = FindAllRestaurantsRequestDTOBuilder
                .oneFindAllRestaurantsRequestDTO()
                .build();

        List<RestaurantResponseDTO> expectedDTOList = List.of(
                RestaurantResponseDTOBuilder.oneRestaurantResponseDTO().build(),
                RestaurantResponseDTOBuilder.oneRestaurantResponseDTO().withId(2L).withName("Restaurante Teste 2")
                        .withType("Tipo Teste 2").build()
        );

        List<Restaurant> returnedRestaurants = List.of(
                RestaurantBuilder.oneRestaurant().withId(1L).withName("Restaurante Teste").withType("Tipo Teste").build(),
                RestaurantBuilder.oneRestaurant().withId(2L).withName("Restaurante Teste 2").withType("Tipo Teste 2").build()
        );

        when(restaurantRepository.findAll(anyInt(), anyLong()))
                .thenReturn(returnedRestaurants);

        // Act
        List<RestaurantResponseDTO> responseList = findAllRestaurantsUseCase.execute(requestDTO);

        // Assert
        verify(restaurantRepository, times(1)).findAll(anyInt(), anyLong());
        verify(restaurantRepository, never()).findAllOpen(anyInt(), anyLong());

        assertThat(responseList).isNotNull();
        assertThat(responseList).hasSize(expectedDTOList.size());
        assertThat(responseList.getFirst().id()).isEqualTo(expectedDTOList.getFirst().id());
        assertThat(responseList.getFirst().name()).isEqualTo(expectedDTOList.getFirst().name());
        assertThat(responseList.getFirst().type()).isEqualTo(expectedDTOList.getFirst().type());
        assertThat(responseList.getFirst().ownerId()).isEqualTo(expectedDTOList.getFirst().ownerId());
        assertThat(responseList.getFirst().address()).isEqualTo(expectedDTOList.getFirst().address());
        assertThat(responseList.getFirst().startTime()).isEqualTo(expectedDTOList.getFirst().startTime());
        assertThat(responseList.getFirst().endTime()).isEqualTo(expectedDTOList.getFirst().endTime());

        assertThat(responseList.get(1).id()).isEqualTo(expectedDTOList.get(1).id());
        assertThat(responseList.get(1).name()).isEqualTo(expectedDTOList.get(1).name());
        assertThat(responseList.get(1).type()).isEqualTo(expectedDTOList.get(1).type());
        assertThat(responseList.get(1).ownerId()).isEqualTo(expectedDTOList.get(1).ownerId());
        assertThat(responseList.get(1).address()).isEqualTo(expectedDTOList.get(1).address());
        assertThat(responseList.get(1).startTime()).isEqualTo(expectedDTOList.get(1).startTime());
        assertThat(responseList.get(1).endTime()).isEqualTo(expectedDTOList.get(1).endTime());
    }

    @Test
    void shouldExecuteAndReturnAllOpenRestaurants() {
        // Arrange
        FindAllRestaurantsRequestDTO requestDTO = FindAllRestaurantsRequestDTOBuilder
                .oneFindAllRestaurantsRequestDTO()
                .withOnlyOpen(true)
                .build();

        List<RestaurantResponseDTO> expectedDTOList = List.of(
                RestaurantResponseDTOBuilder.oneRestaurantResponseDTO().build()
        );

        List<Restaurant> returnedRestaurants = List.of(
                RestaurantBuilder.oneRestaurant().withId(1L).withName("Restaurante Teste").withType("Tipo Teste").build()
        );

        when(restaurantRepository.findAllOpen(anyInt(), anyLong()))
                .thenReturn(returnedRestaurants);

        // Act
        List<RestaurantResponseDTO> responseList = findAllRestaurantsUseCase.execute(requestDTO);

        // Assert
        verify(restaurantRepository, times(1)).findAllOpen(anyInt(), anyLong());
        verify(restaurantRepository, never()).findAll(anyInt(), anyLong());

        assertThat(responseList).isNotNull();
        assertThat(responseList).hasSize(expectedDTOList.size());
        assertThat(responseList.getFirst().id()).isEqualTo(expectedDTOList.getFirst().id());
        assertThat(responseList.getFirst().name()).isEqualTo(expectedDTOList.getFirst().name());
        assertThat(responseList.getFirst().type()).isEqualTo(expectedDTOList.getFirst().type());
        assertThat(responseList.getFirst().ownerId()).isEqualTo(expectedDTOList.getFirst().ownerId());
        assertThat(responseList.getFirst().address()).isEqualTo(expectedDTOList.getFirst().address());
        assertThat(responseList.getFirst().startTime()).isEqualTo(expectedDTOList.getFirst().startTime());
        assertThat(responseList.getFirst().endTime()).isEqualTo(expectedDTOList.getFirst().endTime());
    }
}
