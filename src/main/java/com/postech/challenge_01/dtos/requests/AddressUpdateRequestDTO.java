package com.postech.challenge_01.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record AddressUpdateRequestDTO(
        @NotNull(message = "O id do usuário não pode ser nulo")
        Long id,
        @NotNull(message = "AddressRequestDTO não pode ser nulo")
        AddressRequestDTO data
) {
}
