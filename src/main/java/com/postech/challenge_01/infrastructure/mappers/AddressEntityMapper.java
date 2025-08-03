package com.postech.challenge_01.infrastructure.mappers;

import com.postech.challenge_01.dtos.transfer.address.AddressDTO;
import com.postech.challenge_01.dtos.transfer.address.NewAddressDTO;
import com.postech.challenge_01.infrastructure.entities.AddressEntity;

public class AddressEntityMapper {
    public static AddressDTO toAddressDTO(AddressEntity source)  {
        return new AddressDTO(
                source.getId(),
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

    public static AddressEntity toAddressEntity(AddressDTO source) {
        var target = new AddressEntity();

        target.setId(source.id());
        target.setStreet(source.street());
        target.setNumber(source.number());
        target.setComplement(source.complement());
        target.setNeighborhood(source.neighborhood());
        target.setCity(source.city());
        target.setState(source.state());
        target.setCountry(source.country());
        target.setPostalCode(source.postalCode());
        target.setLastModifiedDateTime(source.lastModifiedDateTime());

        return target;
    }

    public static AddressEntity toAddressEntity(NewAddressDTO source) {
        var target = new AddressEntity();

        target.setStreet(source.street());
        target.setNumber(source.number());
        target.setComplement(source.complement());
        target.setNeighborhood(source.neighborhood());
        target.setCity(source.city());
        target.setState(source.state());
        target.setCountry(source.country());
        target.setPostalCode(source.postalCode());

        return target;
    }
}
