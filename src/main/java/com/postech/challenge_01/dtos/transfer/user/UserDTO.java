package com.postech.challenge_01.dtos.transfer.user;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        Long userTypeId,
        String name,
        String email,
        String login,
        String password,
        LocalDateTime lastModifiedDateTime
) {}
