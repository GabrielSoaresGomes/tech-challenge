package com.postech.challenge_01.dtos.requests.user_type;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;

public record FindAllUserTypesRequestDTO(
        @NotNull(message = "Pageable não pode ser nulo")
        Pageable pageable
) {}
