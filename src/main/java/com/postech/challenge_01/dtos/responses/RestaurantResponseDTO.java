package com.postech.challenge_01.dtos.responses;

import java.time.LocalTime;

public record RestaurantResponseDTO(
        Long id,
        Long ownerId,
        Long addressId,
        String name,
        String type,
        LocalTime startTime,
        LocalTime endTime
) {
}
