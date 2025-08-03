package com.postech.challenge_01.builder.address;

import com.postech.challenge_01.dtos.transfer.address.AddressDTO;

import java.time.LocalDateTime;

public class AddressDTOBuilder {
    private Long id = 1L;
    private String street = "Rua Padrão";
    private String number = "123";
    private String complement = "Apto 1";
    private String neighborhood = "Centro";
    private String city = "Cidade Exemplo";
    private String state = "Estado Exemplo";
    private String country = "País Exemplo";
    private String postalCode = "00000-000";
    private LocalDateTime createdAt = LocalDateTime.now();

    public static AddressDTOBuilder oneAddressDTO() {
        return new AddressDTOBuilder();
    }

    public AddressDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AddressDTOBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressDTOBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public AddressDTOBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public AddressDTOBuilder withNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public AddressDTOBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressDTOBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressDTOBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressDTOBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressDTOBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AddressDTO build() {
        return new AddressDTO(
                id,
                street,
                number,
                complement,
                neighborhood,
                city,
                state,
                country,
                postalCode,
                createdAt
        );
    }
}
