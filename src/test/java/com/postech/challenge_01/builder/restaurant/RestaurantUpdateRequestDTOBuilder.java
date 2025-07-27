package com.postech.challenge_01.builder.restaurant;

import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateDataDTO;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateRequestDTO;

public class RestaurantUpdateRequestDTOBuilder {
    private Long id = 1L;
    private RestaurantUpdateDataDTO restaurantUpdateDataDTO = RestaurantUpdateDataDTOBuilder
            .oneRestaurantUpdateDataDTO()
            .build();

    public static RestaurantUpdateRequestDTOBuilder oneRestaurantUpdateRequestDTO() {
        return new RestaurantUpdateRequestDTOBuilder();
    }

    public RestaurantUpdateRequestDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RestaurantUpdateRequestDTOBuilder withRestaurant(RestaurantUpdateDataDTO restaurantUpdateDataDTO) {
        this.restaurantUpdateDataDTO = restaurantUpdateDataDTO;
        return this;
    }

    public RestaurantUpdateRequestDTO build() {
        return new RestaurantUpdateRequestDTO(
                id,
                restaurantUpdateDataDTO
        );
    }
}
