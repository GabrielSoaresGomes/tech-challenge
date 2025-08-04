package com.postech.challenge_01.infrastructure.api;

import com.postech.challenge_01.dtos.requests.user_type.UserTypeRequestDTO;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeUpdateDataDTO;
import com.postech.challenge_01.dtos.responses.user_type.UserTypeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Types", description = "Endpoints para gerenciamento de tipos de usuários")
public interface UserTypeRestApi {

    @Operation(
            summary = "Busca por todos os tipos de usuários",
            description = "Busca por todos os tipos de usuários, informe o número de tipos exibidos por página",
            tags = {"User Types"}
    )
    public List<UserTypeResponseDTO> getUserTypes(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );

    @Operation(
            summary = "Busca por somente um tipo de usuário",
            description = "Busca tipo de usuário pelo id, informe id do tipo de usuário",
            tags = {"User Types"}
    )
    public UserTypeResponseDTO getUserTypeById(
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Cria um tipo de usuário",
            description = "Cria um tipo de usuário, informe o nome do tipo de usuário",
            tags = {"User Types"}
    )
    public UserTypeResponseDTO saveUserType(
            @RequestBody UserTypeRequestDTO userTypeRequestDTO
    );

    @Operation(
            summary = "Atualize um tipo de usuário",
            description = "Atualize um tipo de usuário, informe o campo que deseja alterar",
            tags = {"User Types"}
    )
    public UserTypeResponseDTO updateUserType(
            @RequestBody @Valid UserTypeUpdateDataDTO userTypeRequest,
            @PathVariable(value = "id") Long id
    );

    @Operation(
            summary = "Exclua um tipo de usuário",
            description = "Exclua um tipo de usuário, informe o id do tipo de usuário",
            tags = {"User Types"}
    )
    public void deleteUserType(
            @PathVariable("id") Long id
    );
}
