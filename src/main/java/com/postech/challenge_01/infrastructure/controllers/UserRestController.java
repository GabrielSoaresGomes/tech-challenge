package com.postech.challenge_01.infrastructure.controllers;

import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.dtos.requests.address.NewAddressWithUserRequestDTO;
import com.postech.challenge_01.dtos.requests.address.FindAddressRequestDTO;
import com.postech.challenge_01.dtos.requests.address.FindAllAddressesByUserIdRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserPasswordRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserUpdatePasswordRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.address.AddressResponseDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.infrastructure.api.UserRestApi;
import com.postech.challenge_01.interface_adapter.controllers.UserController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController implements UserRestApi {
    private final UserController controller;

    @Override
    @GetMapping
    public List<UserResponseDTO> getUser(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return this.controller.getUser(PageRequest.of(page, size));
    }

    @Override
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(
            @PathVariable("id") Long id
    ) {
        return this.controller.getUserById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO saveUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO
    ) {
        return this.controller.saveUser(userRequestDTO);
    }

    @Override
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(
            @RequestBody @Valid UserRequestDTO userRequestDTO,
            @PathVariable(value = "id") Long id
    ) {
        var updateRequest = new UserUpdateRequestDTO(id, userRequestDTO);
        return this.controller.updateUser(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable("id") Long id
    ) {
        this.controller.deleteUser(id);
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
        return this.controller.getAddressesByUserId(listRequest);
    }

    @Override
    @PostMapping("/{id}/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponseDTO saveAddress(
            @PathVariable("id") Long id,
            @RequestBody @Valid AddressRequestDTO addressRequestDTO
    ) {
        NewAddressWithUserRequestDTO dto = new NewAddressWithUserRequestDTO(
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

    @Override
    @GetMapping("/{id}/addresses/{addressId}")
    public AddressResponseDTO getAddressById(
            @PathVariable("id") Long id,
            @PathVariable("addressId") Long addressId
    ) {
        var findAddressRequest = new FindAddressRequestDTO(id, addressId);
        return this.controller.getAddressById(findAddressRequest);
    }

    @Override
    @PutMapping("/{id}/senha")
    public void updatePassword(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdatePasswordRequestDTO request) {

        var updatePasswordRequest = new UserPasswordRequestDTO(id, request.password());
        this.controller.updatePassword(updatePasswordRequest);
    }
}
