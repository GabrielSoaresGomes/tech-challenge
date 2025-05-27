package com.postech.challenge_01.controllers;

import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.usecases.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Users", description = "Endpoints para gerenciamento de usuários")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final SaveUserUseCase saveUserUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

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
        return this.findAllUsersUseCase.execute(page, size);
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
        return this.findUserByIdUseCase.execute(id);
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
        return this.saveUserUseCase.execute(userRequestDTO);
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
        return this.updateUserUseCase.execute(userRequestDTO, id);
    }
    @Operation(
            summary = "Delete um usuário",
            description = "Delete um usuário, informe o id do usuário",
            tags = {"Users"}
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable("id") Long id
    ) {
        this.deleteUserUseCase.execute(id);
    }
}
