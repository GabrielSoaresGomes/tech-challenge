package com.postech.challenge_01.infrastructure.api;

import com.postech.challenge_01.dtos.requests.address.AddressUpdateDataDTO;
import com.postech.challenge_01.dtos.responses.address.AddressResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Addresses", description = "Endpoints para gerenciamento de endereços")
public interface AddressApi {

    @Operation(
            summary = "Busca por todos os endereços",
            description = "Busca por todos os endereços, informe o número de endereços exibidos por página",
            tags = {"Addresses"}
    )
    public List<AddressResponseDTO> getAddress(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );

    @Operation(
            summary = "Busca por somente um endereço",
            description = "Busca endereço pelo id, informe id do endereço",
            tags = {"Addresses"}
    )
    public AddressResponseDTO getAddressById(
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Atualize um endereço",
            description = "Atualize um endereço, informe o campo que deseja alterar",
            tags = {"Addresses"}
    )
    public AddressResponseDTO updateAddress(
            @RequestBody @Valid AddressUpdateDataDTO addressUpdateDataDTO,
            @PathVariable(value = "id") Long id
    );

    @Operation(
            summary = "Exclua um endereço",
            description = "Exclua um endereço, informe o id do endereço",
            tags = {"Addresses"}
    )
    public void deleteAddress(
            @PathVariable("id") Long id
    );

}