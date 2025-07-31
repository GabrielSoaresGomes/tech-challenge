package com.postech.challenge_01.mappers;

import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.transfer.address.AddressDTO;
import com.postech.challenge_01.dtos.transfer.address.NewAddressDTO;
import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.dtos.requests.address.AddressWithUserRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;

import java.util.List;

public class AddressMapper {
    public static Address addressRequestDTOToAddress(
            AddressRequestDTO dto
    ) {
        return addressRequestDTOToAddress(null, dto);
    }

    public static Address addressRequestDTOToAddress(
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

    public static AddressResponseDTO addressToAddressResponseDTO(Address entity) {
        return new AddressResponseDTO(
                entity.getId(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getComplement(),
                entity.getNeighborhood(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getPostalCode()
        );
    }

    public static List<AddressResponseDTO> addressToAddressResponseDTOList(List<Address> entities) {
        return entities.stream()
                .map(AddressMapper::addressToAddressResponseDTO)
                .toList();
    }

    public static Address addressWithUserRequestDTOToAddress(
            AddressWithUserRequestDTO dto
    ) {
        return new Address(
                null,
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

    public static Address toAddress(AddressDTO source) {
        return new Address(
                source.id(),
                source.street(),
                source.number(),
                source.complement(),
                source.neighborhood(),
                source.city(),
                source.state(),
                source.country(),
                source.postalCode(),
                source.lastModifiedDateTime()
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
}
