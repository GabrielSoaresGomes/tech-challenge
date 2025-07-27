package com.postech.challenge_01.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record UserTypeRequestDTO(
        @NotNull(message = "O nome do usuário não pode ser nulo")
        String name
) {}
