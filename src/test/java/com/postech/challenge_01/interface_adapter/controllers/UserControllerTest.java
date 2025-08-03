package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.address.FindAddressByIdAndUserIdUseCase;
import com.postech.challenge_01.application.usecases.address.FindAllAddressesByUserIdUseCase;
import com.postech.challenge_01.application.usecases.address.SaveUserAddressUseCase;
import com.postech.challenge_01.application.usecases.user.*;
import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.interface_adapter.presenters.UserPresenter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Mock
    private SaveUserAddressUseCase saveUserAddresUseCase;
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
                saveUserAddresUseCase,
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
}
