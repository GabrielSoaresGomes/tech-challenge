package com.postech.challenge_01.dtos.responses;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String login,
        String address,
        LocalDateTime lastModifiedDateTime
) {}
