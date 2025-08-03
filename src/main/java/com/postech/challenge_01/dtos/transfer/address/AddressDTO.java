package com.postech.challenge_01.dtos.transfer.address;

import java.time.LocalDateTime;

public record AddressDTO(
        Long id,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String country,
        String postalCode,
        LocalDateTime lastModifiedDateTime
) {
}
