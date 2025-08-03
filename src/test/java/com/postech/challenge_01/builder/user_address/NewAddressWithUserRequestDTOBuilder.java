package com.postech.challenge_01.builder.user_address;

import com.postech.challenge_01.dtos.requests.address.NewAddressWithUserRequestDTO;

public class NewAddressWithUserRequestDTOBuilder {
    private Long userId = 1L;
    private String street = "Rua Padrão";
    private String number = "123";
    private String complement = "Apto 1";
    private String neighborhood = "Centro";
    private String city = "Cidade Exemplo";
    private String state = "Estado Exemplo";
    private String country = "País Exemplo";
    private String postalCode = "00000-000";

    public static NewAddressWithUserRequestDTOBuilder oneRequestDTO() {
        return new NewAddressWithUserRequestDTOBuilder();
    }

    public NewAddressWithUserRequestDTOBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public NewAddressWithUserRequestDTO build() {
        return new NewAddressWithUserRequestDTO(
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
