package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.user.DeleteUserUseCase;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DeleteUserUseCaseTest {
    @Mock
    private IUserGateway gateway;

    @InjectMocks
    private DeleteUserUseCase useCase;

    @Mock
    private IAddressGateway addressGateway;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldDeleteUserSuccessfully() {
        Long userId = 1L;

        Mockito.doNothing().when(addressGateway).deleteByUserId(userId);
        Mockito.doNothing().when(gateway).delete(userId);

        useCase.execute(userId);

        Mockito.verify(addressGateway).deleteByUserId(userId);
        Mockito.verify(gateway).delete(userId);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        Long userId = 1L;

        Mockito.doNothing().when(addressGateway).deleteByUserId(userId);
        Mockito.doThrow(UserNotFoundException.class).when(gateway).delete(userId);

        Assertions.assertThrows(UserNotFoundException.class, () -> useCase.execute(userId));

        Mockito.verify(addressGateway).deleteByUserId(userId);
        Mockito.verify(gateway).delete(userId);
    }
}
