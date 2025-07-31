package com.postech.challenge_01.infrastructure.controllers;

import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.dtos.requests.address.AddressUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.application.usecases.address.DeleteAddressUseCase;
import com.postech.challenge_01.application.usecases.address.FindAddressByIdUseCase;
import com.postech.challenge_01.application.usecases.address.FindAllAddressesUseCase;
import com.postech.challenge_01.application.usecases.address.UpdateAddressUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Addresses", description = "Endpoints para gerenciamento de endereços")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final FindAllAddressesUseCase findAllAddressesUseCase;
    private final FindAddressByIdUseCase findAddressByIdUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;

    @Operation(
            summary = "Busca por todos os endereços",
            description = "Busca por todos os endereços, informe o número de endereços exibidos por página",
            tags = {"Addresses"}
    )
    @GetMapping
    public List<AddressResponseDTO> getAddress(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return this.findAllAddressesUseCase.execute(PageRequest.of(page, size));
    }

    @Operation(
            summary = "Busca por somente um endereço",
            description = "Busca endereço pelo id, informe id do endereço",
            tags = {"Addresses"}
    )
    @GetMapping("/{id}")
    public AddressResponseDTO getAddressById(
            @PathVariable("id") Long id
    ) {
        return this.findAddressByIdUseCase.execute(id);
    }

    @Operation(
            summary = "Atualize um endereço",
            description = "Atualize um endereço, informe o campo que deseja alterar",
            tags = {"Addresses"}
    )
    @PutMapping("/{id}")
    public AddressResponseDTO updateAddress(
            @RequestBody @Valid AddressRequestDTO addressRequestDTO,
            @PathVariable(value = "id") Long id
    ) {
        var updateRequest = new AddressUpdateRequestDTO(id, addressRequestDTO);
        return this.updateAddressUseCase.execute(updateRequest);
    }

    @Operation(
            summary = "Exclua um endereço",
            description = "Exclua um endereço, informe o id do endereço",
            tags = {"Addresses"}
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(
            @PathVariable("id") Long id
    ) {
        this.deleteAddressUseCase.execute(id);
    }
}
