package com.postech.challenge_01.application.mappers;

import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.requests.address.AddressUpdateDataDTO;
import com.postech.challenge_01.dtos.transfer.address.AddressDTO;
import com.postech.challenge_01.dtos.transfer.address.NewAddressDTO;
import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;


public class AddressMapper {
    public static Address toAddress(
            AddressRequestDTO dto
    ) {
        return toAddress(null, dto);
    }

    public static Address toAddress(
            Long id,
            AddressRequestDTO dto
    ) {
        return new Address(
                id,
                dto.street(),
                dto.number(),
                dto.complement(),
                dto.neighborhood(),
                dto.city(),
                dto.state(),
                dto.country(),
                dto.postalCode()
        );
    }

    public static Address toAddress(
            Long id,
            AddressUpdateDataDTO dto
    ) {
        return new Address(
                id,
                dto.street(),
                dto.number(),
                dto.complement(),
                dto.neighborhood(),
                dto.city(),
                dto.state(),
                dto.country(),
                dto.postalCode()
        );
    }

    public static Address toAddress(AddressDTO target) {
        return new Address(
                target.id(),
                target.street(),
                target.number(),
                target.complement(),
                target.neighborhood(),
                target.city(),
                target.state(),
                target.country(),
                target.postalCode()
        );
    }

    public static AddressDTO toAddressDTO(Address source, Long id) {
        return new AddressDTO(
                id,
                source.getStreet(),
                source.getNumber(),
                source.getComplement(),
                source.getNeighborhood(),
                source.getCity(),
                source.getState(),
                source.getCountry(),
                source.getPostalCode(),
                source.getLastModifiedDateTime()
        );
    }

    public static NewAddressDTO toNewAddressDTO(Address source) {
        return new NewAddressDTO(
                source.getStreet(),
                source.getNumber(),
                source.getComplement(),
                source.getNeighborhood(),
                source.getCity(),
                source.getState(),
                source.getCountry(),
                source.getPostalCode()
        );
    }
}
