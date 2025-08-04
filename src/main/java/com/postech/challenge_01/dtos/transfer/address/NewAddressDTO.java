package com.postech.challenge_01.dtos.transfer.address;

public record NewAddressDTO(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String country,
        String postalCode
) {}
