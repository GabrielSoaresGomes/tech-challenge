package com.postech.challenge_01.dtos.requests.address;

import jakarta.validation.constraints.NotNull;

public record FindAddressRequestDTO(
        Long userId,
        @NotNull(message = "O id do endereço não pode ser nulo")
        Long addressId
) {
}
