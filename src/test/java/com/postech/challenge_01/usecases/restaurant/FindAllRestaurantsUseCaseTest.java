package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.builder.restaurant.FindAllRestaurantsRequestDTOBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantResponseDTOBuilder;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;
import com.postech.challenge_01.dtos.requests.restaurant.FindAllRestaurantsRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.application.usecases.restaurant.FindAllRestaurantsUseCase;
import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FindAllRestaurantsUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private IRestaurantGateway restaurantGateway;

    @InjectMocks
    private FindAllRestaurantsUseCase findAllRestaurantsUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        findAllRestaurantsUseCase = new FindAllRestaurantsUseCase(restaurantGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldExecuteAndReturnAllRestaurants() {
        FindAllRestaurantsRequestDTO requestDTO = FindAllRestaurantsRequestDTOBuilder
                .oneFindAllRestaurantsRequestDTO()
                .build();

        List<RestaurantResponseDTO> expectedDTOList = List.of(
                RestaurantResponseDTOBuilder.oneRestaurantResponseDTO().build(),
                RestaurantResponseDTOBuilder.oneRestaurantResponseDTO().withId(2L).withName("Restaurante Teste 2")
                        .withType(RestaurantGenreEnum.AMERICAN).build()
        );

        List<Restaurant> returnedRestaurants = List.of(
                RestaurantBuilder.oneRestaurant().withId(1L).withName("Restaurante Teste").withType(RestaurantGenreEnum.BRAZILIAN).build(),
                RestaurantBuilder.oneRestaurant().withId(2L).withName("Restaurante Teste 2").withType(RestaurantGenreEnum.AMERICAN).build()
        );

        Pageable pageable = Pageable.ofSize(10).withPage(0);
        when(restaurantGateway.findAll(pageable))
                .thenReturn(returnedRestaurants);

        List<Restaurant> responseList = findAllRestaurantsUseCase.execute(pageable);

        verify(restaurantGateway, times(1)).findAll(Pageable.ofSize(10).withPage(0));
        verify(restaurantGateway, never()).findAllOpen(Pageable.ofSize(10).withPage(0));

        assertThat(responseList).isNotNull();
        assertThat(responseList).hasSize(expectedDTOList.size());
        assertThat(responseList.getFirst().getId()).isEqualTo(expectedDTOList.getFirst().id());
        assertThat(responseList.getFirst().getName()).isEqualTo(expectedDTOList.getFirst().name());
        assertThat(responseList.getFirst().getType()).isEqualTo(expectedDTOList.getFirst().type());
        assertThat(responseList.getFirst().getOwnerId()).isEqualTo(expectedDTOList.getFirst().ownerId());
        assertThat(responseList.getFirst().getAddressId()).isEqualTo(expectedDTOList.getFirst().addressId());
        assertThat(responseList.getFirst().getStartTime()).isEqualTo(expectedDTOList.getFirst().startTime());
        assertThat(responseList.getFirst().getEndTime()).isEqualTo(expectedDTOList.getFirst().endTime());

        assertThat(responseList.get(1).getId()).isEqualTo(expectedDTOList.get(1).id());
        assertThat(responseList.get(1).getName()).isEqualTo(expectedDTOList.get(1).name());
        assertThat(responseList.get(1).getType()).isEqualTo(expectedDTOList.get(1).type());
        assertThat(responseList.get(1).getOwnerId()).isEqualTo(expectedDTOList.get(1).ownerId());
        assertThat(responseList.get(1).getAddressId()).isEqualTo(expectedDTOList.get(1).addressId());
        assertThat(responseList.get(1).getStartTime()).isEqualTo(expectedDTOList.get(1).startTime());
        assertThat(responseList.get(1).getEndTime()).isEqualTo(expectedDTOList.get(1).endTime());
    }
}
