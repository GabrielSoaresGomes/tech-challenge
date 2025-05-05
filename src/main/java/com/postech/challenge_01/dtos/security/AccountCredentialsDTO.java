package com.postech.challenge_01.dtos.security;

import jakarta.validation.constraints.NotBlank;

public record AccountCredentialsDTO (
        @NotBlank(message = "O login não pode ser nulo ou vazio")
        String login,

        @NotBlank(message = "A senha não pode ser nula ou vazia")
        String password
) {}
