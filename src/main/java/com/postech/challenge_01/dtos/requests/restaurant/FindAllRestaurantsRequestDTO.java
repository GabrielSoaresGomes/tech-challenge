package com.postech.challenge_01.dtos.requests.restaurant;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;

public record FindAllRestaurantsRequestDTO (
    @NotNull(message = "Pageable não pode ser nulo")
    Pageable pageable,

    Boolean onlyOpen
){
}
