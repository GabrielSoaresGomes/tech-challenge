package com.postech.challenge_01.dtos.requests.user;

import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotNull(message = "O id do tipo de usuário não pode ser nulo")
        Long userTypeId,

        @NotNull(message = "O nome do usuário não pode ser nulo")
        String name,

        @NotNull(message = "O email do usuário não pode ser nulo")
        String email,

        @NotNull(message = "O login do usuário não pode ser nulo")
        String login,

        @NotNull(message = "A senha do usuário não pode ser nula")
        String password
) {
}
