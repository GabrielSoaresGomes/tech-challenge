package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.application.usecases.restaurant.FindAllOpenRestaurantsUseCase;
import com.postech.challenge_01.builder.restaurant.FindAllRestaurantsRequestDTOBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantResponseDTOBuilder;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;
import com.postech.challenge_01.dtos.requests.restaurant.FindAllRestaurantsRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class FindAllOpenRestaurantsUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private IRestaurantGateway restaurantGateway;

    @InjectMocks
    private FindAllOpenRestaurantsUseCase findAllOpenRestaurantsUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        findAllOpenRestaurantsUseCase = new FindAllOpenRestaurantsUseCase(restaurantGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldExecuteAndReturnAllOpenRestaurants() {
        FindAllRestaurantsRequestDTO requestDTO = FindAllRestaurantsRequestDTOBuilder
                .oneFindAllRestaurantsRequestDTO()
                .withOnlyOpen(true)
                .build();

        List<RestaurantResponseDTO> expectedDTOList = List.of(
                RestaurantResponseDTOBuilder.oneRestaurantResponseDTO().build()
        );

        List<Restaurant> returnedRestaurants = List.of(
                RestaurantBuilder.oneRestaurant().withId(1L).withName("Restaurante Teste").withType(RestaurantGenreEnum.BRAZILIAN).build()
        );

        Pageable pageable = Pageable.ofSize(10).withPage(0);
        when(restaurantGateway.findAllOpen(pageable))
                .thenReturn(returnedRestaurants);

        List<Restaurant> responseList = findAllOpenRestaurantsUseCase.execute(pageable);

        verify(restaurantGateway, times(1)).findAllOpen(Pageable.ofSize(10).withPage(0));
        verify(restaurantGateway, never()).findAll(Pageable.ofSize(10).withPage(0));

        assertThat(responseList).isNotNull();
        assertThat(responseList).hasSize(expectedDTOList.size());
        assertThat(responseList.getFirst().getOwnerId()).isEqualTo(expectedDTOList.getFirst().id());
        assertThat(responseList.getFirst().getName()).isEqualTo(expectedDTOList.getFirst().name());
        assertThat(responseList.getFirst().getType()).isEqualTo(expectedDTOList.getFirst().type());
        assertThat(responseList.getFirst().getOwnerId()).isEqualTo(expectedDTOList.getFirst().ownerId());
        assertThat(responseList.getFirst().getAddressId()).isEqualTo(expectedDTOList.getFirst().addressId());
        assertThat(responseList.getFirst().getStartTime()).isEqualTo(expectedDTOList.getFirst().startTime());
        assertThat(responseList.getFirst().getEndTime()).isEqualTo(expectedDTOList.getFirst().endTime());
    }
}
