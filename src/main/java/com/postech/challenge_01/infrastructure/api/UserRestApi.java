package com.postech.challenge_01.infrastructure.api;

import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserUpdatePasswordRequestDTO;
import com.postech.challenge_01.dtos.responses.address.AddressResponseDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Endpoints para gerenciamento de usuários")
public interface UserRestApi {

    @Operation(
            summary = "Busca por todos os usuários",
            description = "Busca por todos os usuários, informe o número de usuários exibidos por página",
            tags = {"Users"}
    )
    public List<UserResponseDTO> getUser(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );

    @Operation(
            summary = "Busca por somente um usuário",
            description = "Busca usuário pelo id, informe id do usuário",
            tags = {"Users"}
    )
    public UserResponseDTO getUserById(
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Cria um usuário",
            description = "Cria um usuário, informe o nome, email, login e senha",
            tags = {"Users"}
    )
    public UserResponseDTO saveUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO
    );

    @Operation(
            summary = "Atualize um usuário",
            description = "Atualize um usuário, informe o campo que deseja alterar",
            tags = {"Users"}
    )
    public UserResponseDTO updateUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO,
            @PathVariable(value = "id") Long id
    );

    @Operation(
            summary = "Exclua um usuário",
            description = "Exclua um usuário, informe o id do usuário",
            tags = {"Users"}
    )
    public void deleteUser(
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Busca dos endereços de um usuário",
            description = "Busca por todos endereços de um usuário, informe o número de endereços exibidos por página",
            tags = {"Users"}
    )
    public List<AddressResponseDTO> getAddressesByUserId(
            @PathVariable("id") Long id,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );

    @Operation(
            summary = "Adiciona endereço para um usuário",
            description = "Adiciona endereço para um usuário, informe usuário, rua, casa, bairro, cidade, estado, país e CEP",
            tags = {"Users"}
    )
    public AddressResponseDTO saveAddress(
            @PathVariable("id") Long id,
            @RequestBody @Valid AddressRequestDTO addressRequestDTO
    );

    @Operation(
            summary = "Busca por somente um endereço do usuário",
            description = "Busca endereço pelo id do usuário e do endereço",
            tags = {"Users"}
    )
    public AddressResponseDTO getAddressById(
            @PathVariable("id") Long id,
            @PathVariable("addressId") Long addressId
    );

    @Operation(
            summary = "Atualiza a senha de um usuário",
            description = "Atualiza a senha de um usuário, informe o id do usuário e a nova senha",
            tags = {"Users"}
    )
    public void updatePassword(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdatePasswordRequestDTO request);
}
