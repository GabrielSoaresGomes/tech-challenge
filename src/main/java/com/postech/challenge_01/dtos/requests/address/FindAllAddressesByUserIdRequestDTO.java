package com.postech.challenge_01.dtos.requests.address;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;

public record FindAllAddressesByUserIdRequestDTO(
        @NotNull(message = "Pageable não pode ser nulo")
        Pageable pageable,
        @NotNull(message = "O id do usuário não pode ser nulo")
        Long userId
) {
}
