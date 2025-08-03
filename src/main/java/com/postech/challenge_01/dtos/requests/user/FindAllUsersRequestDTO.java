package com.postech.challenge_01.dtos.requests.user;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;

public record FindAllUsersRequestDTO (
    @NotNull(message = "Pageable não pode ser nulo")
    Pageable pageable
){}
