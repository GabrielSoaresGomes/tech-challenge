package com.postech.challenge_01.dtos.responses;

import java.time.Instant;

public record ErrorResponseDTO(
        int status,
        String error,
        String message,
        Instant timestamp
) {}
