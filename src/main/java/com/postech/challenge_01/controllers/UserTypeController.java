package com.postech.challenge_01.controllers;

import com.postech.challenge_01.dtos.requests.UserTypeRequestDTO;
import com.postech.challenge_01.dtos.requests.UserTypeUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.UserTypeResponseDTO;
import com.postech.challenge_01.usecases.user_type.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "UsersType", description = "Endpoints para gerenciamento de tipos de usuários")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users_type")
public class UserTypeController {
    private final FindAllUserTypeUseCase findAllUserTypeUseCase;
    private final FindUserTypeByIdUseCase findUserTypeByIdUseCase;
    private final SaveUserTypeUseCase saveUserTypeUseCase;
    private final UpdateUserTypeUseCase updateUserTypeUseCase;
    private final DeleteUserTypeUseCase deleteUserTypeUseCase;

    @GetMapping
    public List<UserTypeResponseDTO> findAllUserType(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return this.findAllUserTypeUseCase.execute(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public UserTypeResponseDTO getUserTypeById(
            @PathVariable("id") Long id
    ) {
        return this.findUserTypeByIdUseCase.execute(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserTypeResponseDTO saveUserType(
            @RequestBody @Valid UserTypeRequestDTO userTypeRequestDTO
    ) {
        return this.saveUserTypeUseCase.execute(userTypeRequestDTO);
    }

    @PutMapping("/{id}")
    public UserTypeResponseDTO updateUserType(
            @RequestBody @Valid UserTypeRequestDTO userTypeRequestDTO,
            @PathVariable(value = "id") Long id
    ) {
        var updateRequest = new UserTypeUpdateRequestDTO(id, userTypeRequestDTO);
        return this.updateUserTypeUseCase.execute(updateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserType(
            @PathVariable("id") Long id
    ) {
        this.deleteUserTypeUseCase.execute(id);
    }

}
