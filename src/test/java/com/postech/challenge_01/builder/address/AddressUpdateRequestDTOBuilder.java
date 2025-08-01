package com.postech.challenge_01.builder.address;

import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.dtos.requests.address.AddressUpdateRequestDTO;

public class AddressUpdateRequestDTOBuilder {

    private Long id;
    private AddressRequestDTO data;

    public static AddressUpdateRequestDTOBuilder oneAddressUpdateRequestDTO() {
        return new AddressUpdateRequestDTOBuilder()
                .withId(1L)
                .withData(AddressRequestDTOBuilder.oneAddressRequestDTO().build());
    }

    public AddressUpdateRequestDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AddressUpdateRequestDTOBuilder withData(AddressRequestDTO data) {
        this.data = data;
        return this;
    }

    public AddressUpdateRequestDTO build() {
        return new AddressUpdateRequestDTO(id, data);
    }
}
