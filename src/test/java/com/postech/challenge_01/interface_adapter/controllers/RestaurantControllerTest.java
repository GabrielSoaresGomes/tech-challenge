package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.restaurant.*;
import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantRequestDTOBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantUpdateDataDTOBuilder;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateDataDTO;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateRequestDTO;
import com.postech.challenge_01.interface_adapter.presenters.RestaurantPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    @Mock
    private FindAllRestaurantsUseCase findAllRestaurantsUseCase;
    @Mock
    private FindRestaurantByIdUseCase findRestaurantByIdUseCase;
    @Mock
    private FindAllOpenRestaurantsUseCase findAllOpenRestaurantsUseCase;
    @Mock
    private SaveRestaurantUseCase saveRestaurantUseCase;
    @Mock
    private UpdateRestaurantUseCase updateRestaurantUseCase;
    @Mock
    private DeleteRestaurantUseCase deleteRestaurantUseCase;

    @InjectMocks
    private RestaurantController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RestaurantController(
                saveRestaurantUseCase,
                findAllRestaurantsUseCase,
                findAllOpenRestaurantsUseCase,
                findRestaurantByIdUseCase,
                updateRestaurantUseCase,
                deleteRestaurantUseCase
        );
    }

    @Test
    void shouldGetAllRestaurants() {
        var pageable = PageRequest.of(0, 10);
        boolean onlyOpen = false;
        var entityList = List.of(RestaurantBuilder.oneRestaurant().build());
        var expectedList = RestaurantPresenter.restaurantsToRestaurantResponseDTOList(entityList);

        when(findAllRestaurantsUseCase.execute(pageable)).thenReturn(entityList);

        var result = controller.getRestaurants(pageable, onlyOpen);

        assertEquals(expectedList, result);
        verify(findAllRestaurantsUseCase, times(1)).execute(pageable);
    }

    @Test
    void shouldGetOnlyOpenRestaurants() {
        var pageable = PageRequest.of(0, 10);
        boolean onlyOpen = true;
        var entityList = List.of(RestaurantBuilder.oneRestaurant().build());
        var expectedList = RestaurantPresenter.restaurantsToRestaurantResponseDTOList(entityList);

        when(findAllOpenRestaurantsUseCase.execute(pageable)).thenReturn(entityList);

        var result = controller.getRestaurants(pageable, onlyOpen);

        assertEquals(expectedList, result);
        verify(findAllOpenRestaurantsUseCase, times(1)).execute(pageable);
    }

    @Test
    void shouldGetRestaurantById() {
        Long id = 1L;
        var entity = RestaurantBuilder.oneRestaurant().withId(id).build();
        var expected = RestaurantPresenter.restaurantToRestaurantResponseDTO(entity);

        when(findRestaurantByIdUseCase.execute(id)).thenReturn(entity);

        var result = controller.getRestaurantById(id);

        assertEquals(expected, result);
        verify(findRestaurantByIdUseCase, times(1)).execute(id);
    }

    @Test
    void shouldSaveRestaurant() {
        RestaurantRequestDTO request = RestaurantRequestDTOBuilder.oneRestaurantRequestDTO().build();
        var entity = RestaurantBuilder.oneRestaurant().build();
        var expected = RestaurantPresenter.restaurantToRestaurantResponseDTO(entity);

        when(saveRestaurantUseCase.execute(request)).thenReturn(entity);

        var result = controller.saveRestaurant(request);

        assertEquals(expected, result);
        verify(saveRestaurantUseCase, times(1)).execute(request);
    }

    @Test
    void shouldUpdateRestaurant() {
        Long id = 1L;
        RestaurantUpdateDataDTO updateData = RestaurantUpdateDataDTOBuilder.oneRestaurantUpdateDataDTO().build();
        RestaurantUpdateRequestDTO updateRequest = new RestaurantUpdateRequestDTO(id, updateData);
        var entity = RestaurantBuilder.oneRestaurant().build();
        var expected = RestaurantPresenter.restaurantToRestaurantResponseDTO(entity);

        when(updateRestaurantUseCase.execute(updateRequest)).thenReturn(entity);

        var result = controller.updateRestaurant(updateRequest);

        assertEquals(expected, result);
        verify(updateRestaurantUseCase, times(1)).execute(updateRequest);
    }

    @Test
    void shouldDeleteRestaurant() {
        Long id = 1L;

        controller.deleteRestaurant(id);

        verify(deleteRestaurantUseCase, times(1)).execute(id);
    }
}
