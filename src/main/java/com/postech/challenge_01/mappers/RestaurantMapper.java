package com.postech.challenge_01.mappers;

import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.dtos.requests.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;

import java.util.List;

public class RestaurantMapper {
    public static Restaurant restaurantRequestDTOToRestaurant(
            RestaurantRequestDTO dto
    ) {
        return restaurantRequestDTOToRestaurant(null, dto);
    }

    public static Restaurant restaurantRequestDTOToRestaurant(
            Long id,
            RestaurantRequestDTO dto
    ) {
        return new Restaurant(
                id,
                dto.ownerId(),
                dto.addressId(),
                dto.name(),
                dto.type(),
                dto.startTime(),
                dto.endTime()
        );
    }

    public static RestaurantResponseDTO restaurantToRestaurantResponseDTO(Restaurant entity) {
        return new RestaurantResponseDTO(
                entity.getId(),
                entity.getOwnerId(),
                entity.getAddressId(),
                entity.getName(),
                entity.getType(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }

    public static List<RestaurantResponseDTO> restaurantToRestaurantResponseDTOList(List<Restaurant> entities) {
        return entities.stream()
                .map(RestaurantMapper::restaurantToRestaurantResponseDTO)
                .toList();
    }
}
