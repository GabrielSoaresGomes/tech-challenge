package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.address.FindAddressByIdAndUserIdUseCase;
import com.postech.challenge_01.application.usecases.address.FindAllAddressesByUserIdUseCase;
import com.postech.challenge_01.application.usecases.address.SaveUserAddressUseCase;
import com.postech.challenge_01.application.usecases.user.*;
import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.dtos.requests.address.FindAddressRequestDTO;
import com.postech.challenge_01.dtos.requests.address.FindAllAddressesByUserIdRequestDTO;
import com.postech.challenge_01.dtos.requests.address.NewAddressWithUserRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserPasswordRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserUpdateRequestDTO;
import com.postech.challenge_01.interface_adapter.presenters.AddressPresenter;
import com.postech.challenge_01.interface_adapter.presenters.UserPresenter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Mock
    private SaveUserAddressUseCase saveUserAddressUseCase;
    @Mock
    private SaveUserUseCase saveUserUseCase;
    @Mock
    private FindAllUsersUseCase findAllUsersUseCase;
    @Mock
    private FindUserByIdUseCase findUserByIdUseCase;
    @Mock
    private UpdateUserUseCase updateUserUseCase;
    @Mock
    private DeleteUserUseCase deleteUserUseCase;
    @Mock
    private FindAddressByIdAndUserIdUseCase findAddressByIdAndUserIdUseCase;
    @Mock
    private FindAllAddressesByUserIdUseCase findAllAddressesByUserIdUseCase;
    @Mock
    private UpdateUserPasswordUseCase updateUserPasswordUseCase;

    @Mock
    private UserPresenter userPresenter;

    @InjectMocks
    private UserController controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        controller = new UserController(
                saveUserAddressUseCase,
                saveUserUseCase,
                findAllUsersUseCase,
                findUserByIdUseCase,
                updateUserUseCase,
                deleteUserUseCase,
                findAddressByIdAndUserIdUseCase,
                findAllAddressesByUserIdUseCase,
                updateUserPasswordUseCase
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldGetUserById() {
        // Arrange
        var id = 1L;
        var userTypeId = 1L;
        var name = "teste";
        var email = "teste@domain.com";
        var login = "teste.teste";

        var user = UserBuilder.oneUser()
                .withId(id)
                .withUserTypeId(userTypeId)
                .withName(name)
                .withEmail(email)
                .withLogin(login)
                .build();

        when(findUserByIdUseCase.execute(id)).thenReturn(user);

        // Act
        var response = controller.getUserById(id);

        // Verify
        verify(findUserByIdUseCase, times(1)).execute(id);

        assertEquals(response.id(), id);
        assertEquals(response.userTypeId(), userTypeId);
        assertEquals(response.name(), name);
        assertEquals(response.email(), email);
        assertEquals(response.login(), login);
    }

    @Test
    void shouldGetAllUsers() {
        // Arrange
        var userList = List.of(UserBuilder.oneUser().build());
        var pageable = PageRequest.of(0, 10);
        var expectedResponseList = UserPresenter.userToUserResponseDTOList(userList);

        when(findAllUsersUseCase.execute(pageable)).thenReturn(userList);

        // Act
        var result = controller.getUser(pageable);

        // Assert
        assertEquals(expectedResponseList, result);
        verify(findAllUsersUseCase, times(1)).execute(pageable);
    }

    @Test
    void shouldSaveUser() {
        // Arrange
        var request = new UserRequestDTO(1L, "name", "email", "login", "password");
        var user = UserBuilder.oneUser().build();
        var expectedResponse = UserPresenter.userToUserResponseDTO(user);

        when(saveUserUseCase.execute(request)).thenReturn(user);

        // Act
        var result = controller.saveUser(request);

        // Assert
        assertEquals(expectedResponse, result);
        verify(saveUserUseCase, times(1)).execute(request);
    }

    @Test
    void shouldSaveAddress() {
        // Arrange
        Long userId = 1L;
        NewAddressWithUserRequestDTO requestDTO = new NewAddressWithUserRequestDTO(
                userId,
                "Rua Exemplo",
                "123",
                "Apto 10",
                "Bairro Centro",
                "São Paulo",
                "SP",
                "Brasil",
                "01234-567"
        );

        var addressEntity = AddressBuilder.oneAddress().build();
        var expectedResponse = AddressPresenter.addressToAddressResponseDTO(addressEntity);

        when(saveUserAddressUseCase.execute(requestDTO)).thenReturn(addressEntity);

        // Act
        var result = controller.saveAddress(requestDTO);

        // Assert
        assertEquals(expectedResponse, result);
        verify(saveUserAddressUseCase, times(1)).execute(requestDTO);
    }

    @Test
    void shouldUpdateUser() {
        // Arrange
        var id = 1L;
        var data = new UserRequestDTO(1L, "updated", "updated@email.com", "updated.login", "pass");
        var request = new UserUpdateRequestDTO(id, data);

        var user = UserBuilder.oneUser().withId(id).build();
        var expectedResponse = UserPresenter.userToUserResponseDTO(user);

        when(updateUserUseCase.execute(request)).thenReturn(user);

        // Act
        var result = controller.updateUser(request);

        // Assert
        assertEquals(expectedResponse, result);
        verify(updateUserUseCase, times(1)).execute(request);
    }

    @Test
    void shouldDeleteUser() {
        // Arrange
        var id = 1L;

        // Act
        controller.deleteUser(id);

        // Assert
        verify(deleteUserUseCase, times(1)).execute(id);
    }

    @Test
    void shouldUpdateUserPassword() {
        // Arrange
        var request = new UserPasswordRequestDTO(1L, "new-password");

        // Act
        controller.updatePassword(request);

        // Assert
        verify(updateUserPasswordUseCase, times(1)).execute(request);
    }

    @Test
    void shouldGetAllAddressesByUserId() {
        // Arrange
        var userId = 1L;
        var pageable = PageRequest.of(0, 10);

        var requestDTO = new FindAllAddressesByUserIdRequestDTO(pageable, userId);
        var addresses = List.of(AddressBuilder.oneAddress().build());
        var expectedResponse = AddressPresenter.addressToAddressesResponseDTOList(addresses);

        when(findAllAddressesByUserIdUseCase.execute(requestDTO)).thenReturn(addresses);

        // Act
        var result = controller.getAddressesByUserId(requestDTO);

        // Assert
        assertEquals(expectedResponse, result);
        verify(findAllAddressesByUserIdUseCase, times(1)).execute(requestDTO);
    }

    @Test
    void shouldGetAddressByIdAndUserId() {
        // Arrange
        var userId = 1L;
        var addressId = 10L;
        var requestDTO = new FindAddressRequestDTO(userId, addressId);
        var address = AddressBuilder.oneAddress().build();
        var expectedResponse = AddressPresenter.addressToAddressResponseDTO(address);

        when(findAddressByIdAndUserIdUseCase.execute(requestDTO)).thenReturn(address);

        // Act
        var result = controller.getAddressById(requestDTO);

        // Assert
        assertEquals(expectedResponse, result);
        verify(findAddressByIdAndUserIdUseCase, times(1)).execute(requestDTO);
    }
}
