package com.postech.challenge_01.dtos.responses;

import com.postech.challenge_01.domains.enums.UserTypeEnum;

public record UserTypeResponseDTO(
        Long id,
        String name,
        UserTypeEnum type
) {}
