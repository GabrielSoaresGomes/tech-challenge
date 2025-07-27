package com.postech.challenge_01.dtos.security;

import java.time.LocalDateTime;

public record TokenDTO(
        String login,
        Boolean authenticated,
        LocalDateTime created,
        LocalDateTime expiration
) {
}