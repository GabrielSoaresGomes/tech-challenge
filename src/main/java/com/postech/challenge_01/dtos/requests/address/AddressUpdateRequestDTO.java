package com.postech.challenge_01.dtos.requests.address;

import jakarta.validation.constraints.NotNull;

public record AddressUpdateRequestDTO(
        @NotNull(message = "O id do usuário não pode ser nulo")
        Long id,
        @NotNull(message = "Data não pode ser nulo")
        AddressUpdateDataDTO data
) {
}
