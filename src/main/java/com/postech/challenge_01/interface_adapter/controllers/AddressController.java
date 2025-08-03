package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.address.*;
import com.postech.challenge_01.dtos.requests.address.AddressUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.address.AddressResponseDTO;
import com.postech.challenge_01.interface_adapter.presenters.AddressPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddressController {
    private final FindAllAddressesUseCase findAllAddressesUseCase;
    private final FindAddressByIdUseCase findAddressByIdUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;

    public List<AddressResponseDTO> getAddress(PageRequest pageRequest) {
        var entityList = this.findAllAddressesUseCase.execute(pageRequest);
        return AddressPresenter.addressToAddressesResponseDTOList(entityList);
    }

    public AddressResponseDTO getAddressById(Long id) {
        var entity = this.findAddressByIdUseCase.execute(id);
        return AddressPresenter.addressToAddressResponseDTO(entity);
    }

    public AddressResponseDTO updateAddress(AddressUpdateRequestDTO addressUpdateRequestDTO) {
        var entity = this.updateAddressUseCase.execute(addressUpdateRequestDTO);
        return AddressPresenter.addressToAddressResponseDTO(entity);
    }

    public void deleteAddress(Long id) {
        this.deleteAddressUseCase.execute(id);
    }

}
