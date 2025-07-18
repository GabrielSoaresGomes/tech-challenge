package com.postech.challenge_01.dtos.requests.restaurant;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record RestaurantRequestDTO(
        @NotNull(message = "O ID do dono não pode ser nulo")
        Long ownerId,

        @NotNull(message = "O ID do endereço não pode ser nulo")
        Long addressId,

        @NotNull(message = "O nome do restaurante não pode ser nulo")
        String name,

        @NotNull(message = "O tipo do restaurante não pode ser nula")
        String type,

        @NotNull(message = "O horário de abertura não pode ser nulo")
        LocalTime startTime,

        @NotNull(message = "O horário de fechamento não pode ser nulo")
        LocalTime endTime
) {
}
