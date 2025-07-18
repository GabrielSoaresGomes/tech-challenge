package com.postech.challenge_01.dtos.requests.user;

import jakarta.validation.constraints.NotNull;

public record UserPasswordRequestDTO(
        @NotNull(message = "O id do usuário não pode ser nulo")
        Long id,
        @NotNull(message = "A senha do usuário não pode ser nula")
        String password
) {
}
