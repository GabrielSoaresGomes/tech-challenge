package com.postech.challenge_01.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record FindAddressRequestDTO(
        @NotNull(message = "O id do usuário não pode ser nulo")
        Long userId,
        @NotNull(message = "O id do endereço não pode ser nulo")
        Long addressId
) {
}
