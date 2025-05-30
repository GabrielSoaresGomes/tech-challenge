package com.postech.challenge_01.dtos.responses;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String login
) {}
