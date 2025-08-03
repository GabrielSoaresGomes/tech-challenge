package com.postech.challenge_01.builder.address;

import com.postech.challenge_01.dtos.requests.address.AddressUpdateDataDTO;
import com.postech.challenge_01.dtos.requests.address.AddressUpdateRequestDTO;

public class AddressUpdateRequestDTOBuilder {
    private Long id = 1L;
    private AddressUpdateDataDTO data = AddressUpdateDataDTOBuilder
            .oneAddressUpdateDataDTO()
            .build();

    public static AddressUpdateRequestDTOBuilder oneAddressUpdateRequestDTO() {
        return new AddressUpdateRequestDTOBuilder();
    }

    public AddressUpdateRequestDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AddressUpdateRequestDTOBuilder withData(AddressUpdateDataDTO data) {
        this.data = data;
        return this;
    }

    public AddressUpdateRequestDTO build() {
        return new AddressUpdateRequestDTO(
                id,
                data
        );
    }


}
