package com.postech.challenge_01.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record RestaurantUpdateRequestDTO(
        @NotNull(message = "O id do restaurante não pode ser nulo")
        Long id,

        @NotNull(message = "RestaurantRequestDTO não pode ser nulo")
        RestaurantRequestDTO data
) {
}
