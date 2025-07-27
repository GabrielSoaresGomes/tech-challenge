package com.postech.challenge_01.usecases.security;

import com.postech.challenge_01.builder.UserBuilder;
import com.postech.challenge_01.dtos.security.AccountCredentialsDTO;
import com.postech.challenge_01.repositories.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthUseCaseTest {
    @Mock
    private UserRepository menuItemRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

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

        when(this.menuItemRepository.findByLogin(anyString())).thenReturn(Optional.of(user));
        when(this.passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        // Act
        var response = this.useCase.execute(credentials);

        // Assert
        verify(this.menuItemRepository).findByLogin(anyString());
        verify(this.passwordEncoder).matches(anyString(), anyString());

        assertNotNull(response);
        assertEquals(credentials.login(), response.login());
        assertEquals(true, response.authenticated());
        assertEquals(response.created().plus(this.validityInMilliseconds, ChronoUnit.MILLIS), response.expiration());
    }

    @Test
    void shouldValidateAndThrowBadCredentials() {
        // Arrange
        var credentials = new AccountCredentialsDTO("login", "password");
        var user = UserBuilder.oneUser()
                .withLogin(credentials.login())
                .build();

        when(this.menuItemRepository.findByLogin(anyString())).thenReturn(Optional.of(user));
        when(this.passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        // Act + Assert
        assertThrows(BadCredentialsException.class, () -> this.useCase.execute(credentials));

        verify(this.menuItemRepository).findByLogin(anyString());
        verify(this.passwordEncoder).matches(anyString(), anyString());
    }

    @Test
    void shouldNotFindUser() {
        // Arrange
        var credentials = new AccountCredentialsDTO("login", "password");

        when(this.menuItemRepository.findByLogin(anyString())).thenReturn(Optional.empty());
        when(this.passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        // Act + Assert
        assertThrows(UsernameNotFoundException.class, () -> this.useCase.execute(credentials));

        verify(this.menuItemRepository).findByLogin(anyString());
        verify(this.passwordEncoder, never()).matches(anyString(), anyString());
    }
}