package com.postech.challenge_01.dtos.requests.user_type;

import com.postech.challenge_01.domains.enums.UserTypeEnum;
import jakarta.validation.constraints.NotNull;

public record UserTypeRequestDTO(
        @NotNull(message = "O nome do tipo do usuário não pode ser nulo")
        String name,

        @NotNull(message = "O tipo do usuário não pode ser nulo")
        UserTypeEnum type
) {}
