package com.postech.challenge_01.builder.address;

import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;

public class AddressRequestDTOBuilder {
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    public static AddressRequestDTOBuilder oneAddressRequestDTO() {
        return new AddressRequestDTOBuilder()
                .withStreet("Rua Padrão")
                .withNumber("123")
                .withComplement("Apto 1")
                .withNeighborhood("Centro")
                .withCity("Cidade Exemplo")
                .withState("Estado Exemplo")
                .withCountry("País Exemplo")
                .withPostalCode("00000-000");
    }

    public AddressRequestDTOBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressRequestDTOBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public AddressRequestDTOBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public AddressRequestDTOBuilder withNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public AddressRequestDTOBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressRequestDTOBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressRequestDTOBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressRequestDTOBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressRequestDTO build() {
        return new AddressRequestDTO(
                street,
                number,
                complement,
                neighborhood,
                city,
                state,
                country,
                postalCode
        );
    }
}
