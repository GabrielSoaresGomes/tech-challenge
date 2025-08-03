package com.postech.challenge_01.builder.address;

import com.postech.challenge_01.dtos.requests.address.AddressUpdateDataDTO;

public class AddressUpdateDataDTOBuilder {
    private String street = "Rua Padrão";
    private String number = "123";
    private String complement = "Apto 1";
    private String neighborhood = "Centro";
    private String city = "Cidade Exemplo";
    private String state = "Estado Exemplo";
    private String country = "País Exemplo";
    private String postalCode = "00000-000";

    public static AddressUpdateDataDTOBuilder oneAddressUpdateDataDTO() {
        return new AddressUpdateDataDTOBuilder();
    }

    public AddressUpdateDataDTOBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressUpdateDataDTOBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public AddressUpdateDataDTOBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public AddressUpdateDataDTOBuilder withNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public AddressUpdateDataDTOBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressUpdateDataDTOBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressUpdateDataDTOBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressUpdateDataDTOBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressUpdateDataDTO build() {
        return new AddressUpdateDataDTO(
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
