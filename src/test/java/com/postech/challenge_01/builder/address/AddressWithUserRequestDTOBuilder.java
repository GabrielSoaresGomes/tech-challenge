package com.postech.challenge_01.builder.address;

import com.postech.challenge_01.dtos.requests.address.AddressWithUserRequestDTO;

public class AddressWithUserRequestDTOBuilder {
    private Long userId;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    public static AddressWithUserRequestDTOBuilder oneAddressWithUserRequestDTO() {
        return new AddressWithUserRequestDTOBuilder()
                .withUserId(1L)
                .withStreet("Rua Teste")
                .withNumber("123")
                .withComplement("Apto 101")
                .withNeighborhood("Centro")
                .withCity("Cidade Exemplo")
                .withState("Estado Exemplo")
                .withCountry("País Exemplo")
                .withPostalCode("00000-000");
    }

    public AddressWithUserRequestDTOBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public AddressWithUserRequestDTOBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressWithUserRequestDTOBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public AddressWithUserRequestDTOBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public AddressWithUserRequestDTOBuilder withNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public AddressWithUserRequestDTOBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressWithUserRequestDTOBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressWithUserRequestDTOBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressWithUserRequestDTOBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressWithUserRequestDTO build() {
        return new AddressWithUserRequestDTO(
                userId,
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
