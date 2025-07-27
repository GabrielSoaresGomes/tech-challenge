package com.postech.challenge_01.dtos.requests.menu;

import jakarta.validation.constraints.NotNull;

public record MenuRequestDTO(
        @NotNull(message = "O ID do restaurante não pode ser nulo.")
        Long restaurantId
) {
}
