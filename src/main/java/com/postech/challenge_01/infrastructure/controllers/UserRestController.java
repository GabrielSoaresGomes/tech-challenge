package com.postech.challenge_01.infrastructure.controllers;

import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.dtos.requests.address.AddressWithUserRequestDTO;
import com.postech.challenge_01.dtos.requests.address.FindAddressRequestDTO;
import com.postech.challenge_01.dtos.requests.address.FindAllAddressesByUserIdRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserPasswordRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserUpdatePasswordRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.interface_adapter.controllers.UserController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Endpoints para gerenciamento de usuários")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserController controller;

    @Operation(
            summary = "Busca por todos os usuários",
            description = "Busca por todos os usuários, informe o número de usuários exibidos por página",
            tags = {"Users"}
    )
    @GetMapping
    public List<UserResponseDTO> getUser(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return this.controller.getUser(PageRequest.of(page, size));
    }

    @Operation(
            summary = "Busca por somente um usuário",
            description = "Busca usuário pelo id, informe id do usuário",
            tags = {"Users"}
    )
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(
            @PathVariable("id") Long id
    ) {
        return this.controller.getUserById(id);
    }

    @Operation(
            summary = "Cria um usuário",
            description = "Cria um usuário, informe o nome, email, login e senha",
            tags = {"Users"}
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO saveUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO
    ) {
        return this.controller.saveUser(userRequestDTO);
    }

    @Operation(
            summary = "Atualize um usuário",
            description = "Atualize um usuário, informe o campo que deseja alterar",
            tags = {"Users"}
    )
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO,
            @PathVariable(value = "id") Long id
    ) {
        var updateRequest = new UserUpdateRequestDTO(id, userRequestDTO);
        return this.controller.updateUser(updateRequest);
    }

    @Operation(
            summary = "Exclua um usuário",
            description = "Exclua um usuário, informe o id do usuário",
            tags = {"Users"}
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable("id") Long id
    ) {
        this.controller.deleteUser(id);
    }

    @Operation(
            summary = "Busca dos endereços de um usuário",
            description = "Busca por todos endereços de um usuário, informe o número de endereços exibidos por página",
            tags = {"Users"}
    )
    @GetMapping("/{id}/addresses")
    public List<AddressResponseDTO> getAddressesByUserId(
            @PathVariable("id") Long id,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var pageable = PageRequest.of(page, size);
        var listRequest = new FindAllAddressesByUserIdRequestDTO(pageable, id);
        return this.controller.getAddressesByUserId(listRequest);
    }

    @Operation(
            summary = "Adiciona endereço para um usuário",
            description = "Adiciona endereço para um usuário, informe usuário, rua, casa, bairro, cidade, estado, país e CEP",
            tags = {"Users"}
    )
    @PostMapping("/{id}/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponseDTO saveAddress(
            @PathVariable("id") Long id,
            @RequestBody @Valid AddressRequestDTO addressRequestDTO
    ) {
        AddressWithUserRequestDTO dto = new AddressWithUserRequestDTO(
                id,
                addressRequestDTO.street(),
                addressRequestDTO.number(),
                addressRequestDTO.complement(),
                addressRequestDTO.neighborhood(),
                addressRequestDTO.city(),
                addressRequestDTO.state(),
                addressRequestDTO.country(),
                addressRequestDTO.postalCode()
        );
        return this.controller.saveAddress(dto);
    }

    @Operation(
            summary = "Busca por somente um endereço do usuário",
            description = "Busca endereço pelo id do usuário e do endereço",
            tags = {"Users"}
    )
    @GetMapping("/{id}/addresses/{addressId}")
    public AddressResponseDTO getAddressById(
            @PathVariable("id") Long id,
            @PathVariable("addressId") Long addressId
    ) {
        var findAddressRequest = new FindAddressRequestDTO(id, addressId);
        return this.controller.getAddressById(findAddressRequest);
    }

    @PutMapping("/{id}/senha")
    public void updatePassword(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdatePasswordRequestDTO request) {

        var updatePasswordRequest = new UserPasswordRequestDTO(id, request.password());
        this.controller.updatePassword(updatePasswordRequest);
    }
}
