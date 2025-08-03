package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantRequestDTOBuilder;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.application.usecases.restaurant.SaveRestaurantUseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaveRestaurantUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private IRestaurantGateway restaurantGateway;

    @Mock
    private IAddressGateway addressGateway;

    @Mock
    private Rule<Restaurant> restaurantRuleMock;

    @Mock
    private Rule<Address> addressRuleMock;

    @InjectMocks
    private SaveRestaurantUseCase saveRestaurantUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        saveRestaurantUseCase = new SaveRestaurantUseCase(restaurantGateway, addressGateway, List.of(restaurantRuleMock), List.of(addressRuleMock));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldExecuteAndSaveRestaurantSuccessfully() {
        Long id = 1L;
        Long expectedAddressId = 1L;
        Address savedAddress = AddressBuilder.oneAddress().build();

        RestaurantRequestDTO requestDTO = RestaurantRequestDTOBuilder
                .oneRestaurantRequestDTO()
                .build();

        Restaurant savedRestaurant = RestaurantBuilder
                .oneRestaurant()
                .withId(id)
                .build();

        when(restaurantGateway.save(any(Restaurant.class))).thenReturn(savedRestaurant);
        when(addressGateway.save(any(Address.class))).thenReturn(savedAddress);

        Restaurant response = saveRestaurantUseCase.execute(requestDTO);

        verify(restaurantRuleMock).execute(any(Restaurant.class));
        verify(addressRuleMock).execute(any(Address.class));
        verify(restaurantGateway, times(1)).save(any(Restaurant.class));

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getOwnerId()).isEqualTo(requestDTO.ownerId());
        assertThat(response.getAddressId()).isEqualTo(expectedAddressId);
        assertThat(response.getName()).isEqualTo(requestDTO.name());
        assertThat(response.getType()).isEqualTo(requestDTO.type());
        assertThat(response.getStartTime()).isEqualTo(requestDTO.startTime());
        assertThat(response.getEndTime()).isEqualTo(requestDTO.endTime());
    }

    @Test
    void shouldThrowInvalidRule() {
        RestaurantRequestDTO requestDTO = RestaurantRequestDTOBuilder
                .oneRestaurantRequestDTO()
                .build();

        doThrow(new RuntimeException("Rule")).when(restaurantRuleMock).execute(any(Restaurant.class));

        assertThrows(RuntimeException.class, () -> saveRestaurantUseCase.execute(requestDTO));
        verify(restaurantGateway, never()).save(any(Restaurant.class));
    }
}
