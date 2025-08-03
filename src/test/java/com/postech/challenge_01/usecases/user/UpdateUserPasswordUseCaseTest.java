package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.application.gateways.IPasswordEncoderGateway;
import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.user.UpdateUserPasswordUseCase;
import com.postech.challenge_01.dtos.requests.user.UserPasswordRequestDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UpdateUserPasswordUseCaseTest {

    @Mock
    private IUserGateway userGateway;

    @Mock
    private IPasswordEncoderGateway passwordEncoderGateway;

    @InjectMocks
    private UpdateUserPasswordUseCase useCase;

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
    void shouldUpdatePasswordSuccessfully() {
        Long userId = 1L;
        String rawPassword = "newPassword";
        String encodedPassword = "encodedPassword";

        UserPasswordRequestDTO request = new UserPasswordRequestDTO(userId, rawPassword);

        Mockito.when(passwordEncoderGateway.encode(rawPassword)).thenReturn(encodedPassword);
        Mockito.doNothing().when(userGateway).updatePassword(userId, encodedPassword);

        useCase.execute(request);

        Mockito.verify(passwordEncoderGateway).encode(rawPassword);
        Mockito.verify(userGateway).updatePassword(userId, encodedPassword);
    }
}
