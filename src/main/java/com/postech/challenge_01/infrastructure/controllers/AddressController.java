package com.postech.challenge_01.infrastructure.controllers;

import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.dtos.requests.address.AddressUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.application.usecases.address.DeleteAddressUseCase;
import com.postech.challenge_01.application.usecases.address.FindAddressByIdUseCase;
import com.postech.challenge_01.application.usecases.address.FindAllAddressesUseCase;
import com.postech.challenge_01.application.usecases.address.UpdateAddressUseCase;
import com.postech.challenge_01.infrastructure.api.AddressApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController implements AddressApi {
    private final FindAllAddressesUseCase findAllAddressesUseCase;
    private final FindAddressByIdUseCase findAddressByIdUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;

    @Override
    @GetMapping
    public List<AddressResponseDTO> getAddress(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return this.findAllAddressesUseCase.execute(PageRequest.of(page, size));
    }

    @Override
    @GetMapping("/{id}")
    public AddressResponseDTO getAddressById(
            @PathVariable("id") Long id
    ) {
        return this.findAddressByIdUseCase.execute(id);
    }

    @Override
    @PutMapping("/{id}")
    public AddressResponseDTO updateAddress(
            @RequestBody @Valid AddressRequestDTO addressRequestDTO,
            @PathVariable(value = "id") Long id
    ) {
        var updateRequest = new AddressUpdateRequestDTO(id, addressRequestDTO);
        return this.updateAddressUseCase.execute(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(
            @PathVariable("id") Long id
    ) {
        this.deleteAddressUseCase.execute(id);
    }
}
