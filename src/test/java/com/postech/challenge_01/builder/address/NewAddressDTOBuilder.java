package com.postech.challenge_01.builder.address;

import com.postech.challenge_01.dtos.transfer.address.NewAddressDTO;

public class NewAddressDTOBuilder {
    private String street = "Rua Padrão";
    private String number = "123";
    private String complement = "Apto 1";
    private String neighborhood = "Centro";
    private String city = "Cidade Exemplo";
    private String state = "Estado Exemplo";
    private String country = "País Exemplo";
    private String postalCode = "00000-000";

    public static NewAddressDTOBuilder oneNewAddressDTO() {
        return new NewAddressDTOBuilder();
    }

    public NewAddressDTOBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public NewAddressDTOBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public NewAddressDTOBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public NewAddressDTOBuilder withNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public NewAddressDTOBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public NewAddressDTOBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public NewAddressDTOBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public NewAddressDTOBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public NewAddressDTO build() {
        return new NewAddressDTO(
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
