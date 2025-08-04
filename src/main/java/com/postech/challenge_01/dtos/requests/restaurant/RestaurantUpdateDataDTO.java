package com.postech.challenge_01.dtos.requests.restaurant;

import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public record RestaurantUpdateDataDTO(
        @NotNull(message = "O ID do dono não pode ser nulo")
        Long ownerId,

        @NotNull(message = "O nome do restaurante não pode ser nulo")
        String name,

        @NotNull(message = "O tipo do restaurante não pode ser nula")
        RestaurantGenreEnum type,

        @NotNull(message = "O horário de abertura não pode ser nulo")
        LocalTime startTime,

        @NotNull(message = "O horário de fechamento não pode ser nulo")
        LocalTime endTime
) {
}