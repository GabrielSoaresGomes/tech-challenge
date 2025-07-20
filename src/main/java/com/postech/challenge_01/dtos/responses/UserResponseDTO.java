package com.postech.challenge_01.dtos.responses;

public record UserResponseDTO(
        Long id,
        Long userTypeId,
        String name,
        String email,
        String login
) {}
