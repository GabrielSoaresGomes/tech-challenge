package com.postech.challenge_01.builder.address;

import com.postech.challenge_01.domain.Address;

import java.time.LocalDateTime;

public class AddressBuilder {
    private Long id;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private LocalDateTime createdAt;

    public static AddressBuilder oneAddress() {
        return new AddressBuilder()
                .withStreet("Rua Padrão")
                .withNumber("123")
                .withComplement("Apto 1")
                .withNeighborhood("Centro")
                .withCity("Cidade Exemplo")
                .withState("Estado Exemplo")
                .withCountry("País Exemplo")
                .withPostalCode("00000-000")
                .withCreatedAt(LocalDateTime.now());
    }

    public AddressBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AddressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public AddressBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public AddressBuilder withNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public AddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Address build() {
        return new Address(
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
