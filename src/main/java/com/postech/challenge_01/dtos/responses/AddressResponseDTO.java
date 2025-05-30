package com.postech.challenge_01.dtos.responses;

public record AddressResponseDTO(
        Long id,
        Long userId,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String country,
        String postalCode
) {
}
