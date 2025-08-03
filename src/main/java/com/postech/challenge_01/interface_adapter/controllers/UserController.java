package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.address.FindAddressByIdAndUserIdUseCase;
import com.postech.challenge_01.application.usecases.address.FindAllAddressesByUserIdUseCase;
import com.postech.challenge_01.application.usecases.address.SaveUserAddressUseCase;
import com.postech.challenge_01.application.usecases.user.*;
import com.postech.challenge_01.dtos.requests.address.AddressWithUserRequestDTO;
import com.postech.challenge_01.dtos.requests.address.FindAddressRequestDTO;
import com.postech.challenge_01.dtos.requests.address.FindAllAddressesByUserIdRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserPasswordRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.address.AddressResponseDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.interface_adapter.presenters.AddressPresenter;
import com.postech.challenge_01.interface_adapter.presenters.UserPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserController {
    private final SaveUserAddressUseCase saveUserAddresUseCase;
    private final SaveUserUseCase saveUserUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindAddressByIdAndUserIdUseCase findAddressByIdAndUserIdUseCase;
    private final FindAllAddressesByUserIdUseCase findAllAddressesByUserIdUseCase;
    private final UpdateUserPasswordUseCase updateUserPasswordUseCase;

    public List<UserResponseDTO> getUser(PageRequest pageRequest) {
        var entityList = this.findAllUsersUseCase.execute(pageRequest);
        return UserPresenter.userToUserResponseDTOList(entityList);
    }

    public UserResponseDTO getUserById(Long id) {
        var entity = this.findUserByIdUseCase.execute(id);
        return UserPresenter.userToUserResponseDTO(entity);
    }

    public UserResponseDTO saveUser(UserRequestDTO requestDTO) {
        var entity = this.saveUserUseCase.execute(requestDTO);
        return UserPresenter.userToUserResponseDTO(entity);
    }

    public UserResponseDTO updateUser(UserUpdateRequestDTO requestDTO) {
        var entity = this.updateUserUseCase.execute(requestDTO);
        return UserPresenter.userToUserResponseDTO(entity);
    }

    public void deleteUser(Long id) {
        this.deleteUserUseCase.execute(id);
    }

    public List<AddressResponseDTO> getAddressesByUserId(FindAllAddressesByUserIdRequestDTO requestDTO) {
        var entityList = this.findAllAddressesByUserIdUseCase.execute(requestDTO);
        return AddressPresenter.addressToAddressesResponseDTOList(entityList);
    }

    public AddressResponseDTO saveAddress(AddressWithUserRequestDTO requestDTO) {
        return this.saveUserAddresUseCase.execute(requestDTO);
    }

    public AddressResponseDTO getAddressById(FindAddressRequestDTO requestDTO) {
        var entity = this.findAddressByIdAndUserIdUseCase.execute(requestDTO);
        return AddressPresenter.addressToAddressResponseDTO(entity);
    }

    public void updatePassword(UserPasswordRequestDTO requestDTO) {
        updateUserPasswordUseCase.execute(requestDTO);
    }
}