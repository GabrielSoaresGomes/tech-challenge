package com.postech.challenge_01.dtos.responses;

import com.postech.challenge_01.domains.Address;

import java.time.LocalTime;

public record RestaurantResponseDTO(
        Long id,
        Long ownerId,
        String name,
        String type,
        LocalTime startTime,
        LocalTime endTime,
        Address address
) {
}
