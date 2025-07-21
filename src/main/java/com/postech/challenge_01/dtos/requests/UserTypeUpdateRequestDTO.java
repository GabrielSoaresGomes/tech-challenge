package com.postech.challenge_01.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record UserTypeUpdateRequestDTO (
        @NotNull(message = "O id do tipo de usuário não pode ser nulo")
        Long id,
        @NotNull(message = "UserTypeRequestDTO não pode ser nulo")
        UserTypeRequestDTO data
) {}
