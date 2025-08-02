package com.postech.challenge_01.dtos.transfer.user_type;

import com.postech.challenge_01.domain.enums.UserTypeEnum;

public record NewUserTypeDTO(
        String name,
        UserTypeEnum type
) {}
