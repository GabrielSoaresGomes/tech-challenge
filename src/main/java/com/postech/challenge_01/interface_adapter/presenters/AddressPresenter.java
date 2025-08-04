package com.postech.challenge_01.interface_adapter.presenters;

import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.responses.address.AddressResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AddressPresenter {

    public static AddressResponseDTO addressToAddressResponseDTO(Address entity) {
        return new  AddressResponseDTO(
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

    public static List<AddressResponseDTO> addressToAddressesResponseDTOList(List<Address> entity) {
        return entity.stream()
                .map(AddressPresenter::addressToAddressResponseDTO)
                .collect(Collectors.toList());
    }
}
