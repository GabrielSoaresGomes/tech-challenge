package com.postech.challenge_01.dtos.requests.user;

import jakarta.validation.constraints.NotNull;

public record UserUpdatePasswordRequestDTO(
        @NotNull(message = "A senha do usuário não pode ser nula")
        String password
) {
}
