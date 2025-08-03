package com.postech.challenge_01.infrastructure.controllers;

import com.postech.challenge_01.dtos.requests.address.AddressUpdateDataDTO;
import com.postech.challenge_01.dtos.requests.address.AddressUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.address.AddressResponseDTO;
import com.postech.challenge_01.infrastructure.api.AddressApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.postech.challenge_01.interface_adapter.controllers.AddressController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressRestController implements AddressApi {
    private final AddressController controller;

    @Override
    @GetMapping
    public List<AddressResponseDTO> getAddress(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var request = PageRequest.of(page, size);

        return this.controller.getAddress(request);
    }

    @Override
    @GetMapping("/{id}")
    public AddressResponseDTO getAddressById(
            @PathVariable("id") Long id
    ) {
        return this.controller.getAddressById(id);
    }

    @Override
    @PutMapping("/{id}")
    public AddressResponseDTO updateAddress(
            @RequestBody @Valid AddressUpdateDataDTO addressUpdateDataDTO,
            @PathVariable(value = "id") Long id
    ) {
        var updateRequest = new AddressUpdateRequestDTO(id, addressUpdateDataDTO);
        return this.controller.updateAddress(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(
            @PathVariable("id") Long id
    ) {
        this.controller.deleteAddress(id);
    }

}
