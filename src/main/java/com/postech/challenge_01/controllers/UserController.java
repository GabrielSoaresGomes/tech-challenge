package com.postech.challenge_01.controllers;

import com.postech.challenge_01.api.UserApi;
import com.postech.challenge_01.dtos.requests.*;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.usecases.address.FindAddressByIdAndUserIdUseCase;
import com.postech.challenge_01.usecases.address.FindAllAddressesByUserIdUseCase;
import com.postech.challenge_01.usecases.user.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController implements UserApi {
    private final SaveUserUseCase saveUserUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindAddressByIdAndUserIdUseCase findAddressByIdAndUserIdUseCase;
    private final FindAllAddressesByUserIdUseCase findAllAddressesByUserIdUseCase;
    private final UpdateUserPasswordUseCase updateUserPasswordUseCase;

    @Override
    @GetMapping
    public List<UserResponseDTO> getUser(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return this.findAllUsersUseCase.execute(PageRequest.of(page, size));
    }

    @Override
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(
            @PathVariable("id") Long id
    ) {
        return this.findUserByIdUseCase.execute(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO saveUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO
    ) {
        return this.saveUserUseCase.execute(userRequestDTO);
    }

    @Override
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO,
            @PathVariable(value = "id") Long id
    ) {
        var updateRequest = new UserUpdateRequestDTO(id, userRequestDTO);
        return this.updateUserUseCase.execute(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable("id") Long id
    ) {
        this.deleteUserUseCase.execute(id);
    }

    @Override
    @GetMapping("/{id}/addresses")
    public List<AddressResponseDTO> getAddressesByUserId(
            @PathVariable("id") Long id,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var pageable = PageRequest.of(page, size);
        var listRequest = new FindAllAddressesByUserIdRequestDTO(pageable, id);
        return this.findAllAddressesByUserIdUseCase.execute(listRequest);
    }

    @Override
    @GetMapping("/{id}/addresses/{addressId}")
    public AddressResponseDTO getAddressById(
            @PathVariable("id") Long id,
            @PathVariable("addressId") Long addressId
    ) {
        var findAddressRequest = new FindAddressRequestDTO(id, addressId);
        return this.findAddressByIdAndUserIdUseCase.execute(findAddressRequest);
    }

    @Override
    @PutMapping("/{id}/senha")
    public void alterarSenha(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdatePasswordRequestDTO request) {

        var updatePasswordRequest = new UserPasswordRequestDTO(id, request.password());
        updateUserPasswordUseCase.execute(updatePasswordRequest);
    }
}
