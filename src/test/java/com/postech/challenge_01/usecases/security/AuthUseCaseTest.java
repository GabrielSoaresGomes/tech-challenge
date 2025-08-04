package com.postech.challenge_01.usecases.security;

import com.postech.challenge_01.application.gateways.IPasswordEncoderGateway;
import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.security.AuthUseCase;
import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.dtos.security.AccountCredentialsDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthUseCaseTest {
    @Mock
    private IUserGateway userGateway;

    @Mock
    private IPasswordEncoderGateway passwordEncoderGateway;

    @InjectMocks
    private AuthUseCase useCase;

    private AutoCloseable closeable;
    private final long validityInMilliseconds = 3_600_000; //1h

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);

        ReflectionTestUtils.setField(this.useCase, "validityInMilliseconds", this.validityInMilliseconds);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldValidateAndReturnToken() {
        // Arrange
        var credentials = new AccountCredentialsDTO("login", "password");
        var user = UserBuilder.oneUser()
                .withLogin(credentials.login())
                .build();

        when(this.userGateway.requireByLogin(anyString())).thenReturn(user);

        // Act
        var response = this.useCase.execute(credentials);

        // Assert
        verify(this.userGateway).requireByLogin(anyString());
        verify(this.passwordEncoderGateway).matches(anyString(), anyString());

        assertNotNull(response);
        assertEquals(credentials.login(), response.login());
        assertEquals(true, response.authenticated());
        assertEquals(response.created().plus(this.validityInMilliseconds, ChronoUnit.MILLIS), response.expiration());
    }
}