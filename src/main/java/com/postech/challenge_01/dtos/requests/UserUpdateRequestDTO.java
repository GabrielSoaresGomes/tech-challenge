package com.postech.challenge_01.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record UserUpdateRequestDTO(
        @NotNull(message = "O id do usuário não pode ser nulo")
        Long id,
        @NotNull(message = "UserRequestDTO não pode ser nulo")
        UserRequestDTO data
) {
}
