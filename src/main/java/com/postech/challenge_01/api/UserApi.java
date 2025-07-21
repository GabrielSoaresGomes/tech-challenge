package com.postech.challenge_01.api;

import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import com.postech.challenge_01.dtos.requests.UserUpdatePasswordRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Users", description = "Endpoints para gerenciamento de usuários")
public interface UserApi {

    @Operation(
            summary = "Busca por todos os usuários",
            description = "Busca por todos os usuários, informe o número de usuários exibidos por página"
    )
    List<UserResponseDTO> getUser(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );

    @Operation(
            summary = "Busca por somente um usuário",
            description = "Busca usuário pelo id, informe id do usuário"
    )
    UserResponseDTO getUserById(
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Cria um usuário",
            description = "Cria um usuário, informe o nome, email, login e senha"
    )
    UserResponseDTO saveUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO
    );

    @Operation(
            summary = "Atualize um usuário",
            description = "Atualize um usuário, informe o campo que deseja alterar"
    )
    UserResponseDTO updateUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO,
            @PathVariable(value = "id") Long id
    );

    @Operation(
            summary = "Exclua um usuário",
            description = "Exclua um usuário, informe o id do usuário"
    )
    void deleteUser(
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Busca dos endereços de um usuário",
            description = "Busca por todos endereços de um usuário, informe o número de endereços exibidos por página"
    )
    List<AddressResponseDTO> getAddressesByUserId(
            @PathVariable("id") Long id,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );

    @Operation(
            summary = "Busca por somente um endereço do usuário",
            description = "Busca endereço pelo id do usuário e do endereço"
    )
    AddressResponseDTO getAddressById(
            @PathVariable("id") Long id,
            @PathVariable("addressId") Long addressId
    );

    @Operation(
            summary = "Altera senha de um usuário",
            description = "Altera a senha de um usuário, informe o id do usuário e a nova senha"
    )
    void alterarSenha(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdatePasswordRequestDTO request
    );
}
