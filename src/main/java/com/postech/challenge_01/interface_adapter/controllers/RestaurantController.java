package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.restaurant.*;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.interface_adapter.presenters.RestaurantPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantController {
    private final SaveRestaurantUseCase saveRestaurantUseCase;
    private final FindAllRestaurantsUseCase findAllRestaurantsUseCase;
    private final FindAllOpenRestaurantsUseCase findAllOpenRestaurantsUseCase;
    private final FindRestaurantByIdUseCase findRestaurantByIdUseCase;
    private final UpdateRestaurantUseCase updateRestaurantUseCase;
    private final DeleteRestaurantUseCase deleteRestaurantUseCase;

    public List<RestaurantResponseDTO> getRestaurants(PageRequest pageRequest, Boolean onlyOpen) {
        var entities = onlyOpen
                ? findAllOpenRestaurantsUseCase.execute(pageRequest)
                : findAllRestaurantsUseCase.execute(pageRequest);
        return RestaurantPresenter.restaurantsToRestaurantResponseDTOList(entities);
    }

    public RestaurantResponseDTO getRestaurantById(Long id) {
        var entity = this.findRestaurantByIdUseCase.execute(id);
        return RestaurantPresenter.restaurantToRestaurantResponseDTO(entity);
    }

    public RestaurantResponseDTO saveRestaurant(RestaurantRequestDTO requestDTO) {
        var entity = this.saveRestaurantUseCase.execute(requestDTO);
        return RestaurantPresenter.restaurantToRestaurantResponseDTO(entity);
    }

    public RestaurantResponseDTO updateRestaurant(RestaurantUpdateRequestDTO requestDTO) {
        var entity = this.updateRestaurantUseCase.execute(requestDTO);
        return RestaurantPresenter.restaurantToRestaurantResponseDTO(entity);
    }

    public void deleteRestaurant(Long id) {
        this.deleteRestaurantUseCase.execute(id);
    }
}
