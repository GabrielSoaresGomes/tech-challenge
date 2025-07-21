package com.postech.challenge_01.builder.restaurant;

import com.postech.challenge_01.dtos.requests.restaurant.FindAllRestaurantsRequestDTO;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class FindAllRestaurantsRequestDTOBuilder {
    private Pageable pageable = PageRequest.of(0, 10);
    private Boolean onlyOpen = false;

    public static FindAllRestaurantsRequestDTOBuilder oneFindAllRestaurantsRequestDTO() {
        return new FindAllRestaurantsRequestDTOBuilder();
    }

    public FindAllRestaurantsRequestDTOBuilder withPageable(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public FindAllRestaurantsRequestDTOBuilder withOnlyOpen(Boolean onlyOpen) {
        this.onlyOpen = onlyOpen;
        return this;
    }

    public FindAllRestaurantsRequestDTO build() {
        return new FindAllRestaurantsRequestDTO(
                pageable,
                onlyOpen
        );
    }
}
