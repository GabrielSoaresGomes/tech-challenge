package com.postech.challenge_01.mappers;

import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateDataDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.dtos.transfer.restaurant.NewRestaurantDTO;
import com.postech.challenge_01.dtos.transfer.restaurant.RestaurantDTO;

import java.util.List;

public class RestaurantMapper {
    public static Restaurant toRestaurant(
            RestaurantRequestDTO dto
    ) {
        return toRestaurant(null, dto);
    }

    public static Restaurant toRestaurant(
            Long id,
            RestaurantRequestDTO dto
    ) {
        return new Restaurant(
                id,
                dto.ownerId(),
                dto.name(),
                dto.type(),
                dto.startTime(),
                dto.endTime()
        );
    }

    public static Restaurant toRestaurant(
            Long id,
            RestaurantUpdateDataDTO dto
    ) {
        return new Restaurant(
                id,
                dto.ownerId(),
                dto.name(),
                dto.type(),
                dto.startTime(),
                dto.endTime()
        );
    }

    public static Restaurant toRestaurant(RestaurantDTO target) {
        return new Restaurant(
                target.id(),
                target.ownerId(),
                target.name(),
                target.type(),
                target.startTime(),
                target.endTime(),
                target.addressId()
        );
    }

    public static RestaurantDTO toRestaurantDTO(Restaurant source, Long id) {
        return new RestaurantDTO(
                id,
                source.getOwnerId(),
                source.getName(),
                source.getType(),
                source.getStartTime(),
                source.getEndTime(),
                source.getAddressId(),
                source.getLastModifiedDateTime()
        );
    }

    public static NewRestaurantDTO toNewRestaurantDTO(Restaurant source) {
        return new NewRestaurantDTO(
                source.getOwnerId(),
                source.getName(),
                source.getType(),
                source.getStartTime(),
                source.getEndTime(),
                source.getAddressId()
        );
    }
}
