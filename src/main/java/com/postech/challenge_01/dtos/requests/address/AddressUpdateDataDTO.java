package com.postech.challenge_01.dtos.requests.address;

import jakarta.validation.constraints.NotNull;

public record AddressUpdateDataDTO(
        @NotNull(message = "O nome da rua não pode ser nulo")
        String street,

        @NotNull(message = "O número da casa não pode ser nulo")
        String number,

        String complement,

        @NotNull(message = "O bairro não pode ser nulo")
        String neighborhood,

        @NotNull(message = "A cidade não pode ser nula")
        String city,

        @NotNull(message = "O estado não pode ser nulo")
        String state,

        @NotNull(message = "O país não pode ser nulo")
        String country,

        @NotNull(message = "O CEP não pode ser nulo")
        String postalCode
) {}
