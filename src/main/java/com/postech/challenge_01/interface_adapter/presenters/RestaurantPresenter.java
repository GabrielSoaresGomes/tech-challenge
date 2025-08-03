package com.postech.challenge_01.interface_adapter.presenters;

import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantPresenter {
    public static RestaurantResponseDTO restaurantToRestaurantResponseDTO(Restaurant entity) {
        return new RestaurantResponseDTO(
                entity.getId(),
                entity.getOwnerId(),
                entity.getName(),
                entity.getType(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getAddressId()
        );
    }

    public static List<RestaurantResponseDTO> restaurantsToRestaurantResponseDTOList(List<Restaurant> entities) {
        return entities.stream()
                .map(RestaurantPresenter::restaurantToRestaurantResponseDTO)
                .collect(Collectors.toList());
    }
}
