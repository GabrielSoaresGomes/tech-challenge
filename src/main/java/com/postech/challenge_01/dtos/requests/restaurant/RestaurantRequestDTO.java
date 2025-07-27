package com.postech.challenge_01.dtos.requests.restaurant;

import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.domains.enums.RestaurantGenreEnum;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record RestaurantRequestDTO(
        @NotNull(message = "O ID do dono não pode ser nulo")
        Long ownerId,

        @NotNull(message = "O nome do restaurante não pode ser nulo")
        String name,

        @NotNull(message = "O tipo do restaurante não pode ser nula")
        RestaurantGenreEnum type,

        @NotNull(message = "O horário de abertura não pode ser nulo")
        LocalTime startTime,

        @NotNull(message = "O horário de fechamento não pode ser nulo")
        LocalTime endTime,

        @NotNull(message = "O endereço do restaurante não pode ser nulo")
        Address address
) {
}
