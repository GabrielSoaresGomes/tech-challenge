package com.postech.challenge_01.dtos.transfer.user_type;

import com.postech.challenge_01.domain.enums.UserTypeEnum;

import java.time.LocalDateTime;

public record UserTypeDTO(
        Long id,
        String name,
        UserTypeEnum type,
        LocalDateTime lastModifiedDateTime
) {}
