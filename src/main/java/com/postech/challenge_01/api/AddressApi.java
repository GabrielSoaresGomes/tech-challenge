package com.postech.challenge_01.api;

import com.postech.challenge_01.dtos.requests.AddressRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Addresses", description = "Endpoints para gerenciamento de endereços")
public interface AddressApi {

    @Operation(
            summary = "Busca por todos os endereços",
            description = "Busca por todos os endereços, informe o número de endereços exibidos por página"
    )
    List<AddressResponseDTO> getAddress(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );

    @Operation(
            summary = "Busca por somente um endereço",
            description = "Busca endereço pelo id, informe id do endereço"
    )
    AddressResponseDTO getAddressById(
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Cria um endereço",
            description = "Cria um endereço, informe usuário, rua, casa, bairro, cidade, estado, país e CEP"
    )
    AddressResponseDTO saveAddress(
            @RequestBody @Valid AddressRequestDTO addressRequestDTO
    );

    @Operation(
            summary = "Atualize um endereço",
            description = "Atualize um endereço, informe o campo que deseja alterar"
    )
    AddressResponseDTO updateAddress(
            @RequestBody @Valid AddressRequestDTO addressRequestDTO,
            @PathVariable(value = "id") Long id
    );

    @Operation(
            summary = "Exclua um endereço",
            description = "Exclua um endereço, informe o id do endereço"
    )
    void deleteAddress(
            @PathVariable("id") Long id
    );
}
