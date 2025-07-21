package com.postech.challenge_01.builder.restaurant;

import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateRequestDTO;

public class RestaurantUpdateRequestDTOBuilder {
    private Long id = 1L;
    private RestaurantRequestDTO restaurant = RestaurantRequestDTOBuilder.oneRestaurantRequestDTO().build();

    public static RestaurantUpdateRequestDTOBuilder oneRestaurantUpdateRequestDTO() {
        return new RestaurantUpdateRequestDTOBuilder();
    }

    public RestaurantUpdateRequestDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RestaurantUpdateRequestDTOBuilder withRestaurant(RestaurantRequestDTO restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public RestaurantUpdateRequestDTO build() {
        return new RestaurantUpdateRequestDTO(
                id,
                restaurant
        );
    }
}
