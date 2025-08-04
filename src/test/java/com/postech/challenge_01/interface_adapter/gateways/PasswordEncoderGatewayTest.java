package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.interface_adapter.data_sources.PasswordEncoderDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PasswordEncoderGatewayTest {
    @Mock
    private PasswordEncoderDataSource passwordEncoderDataSource;

    @InjectMocks
    private PasswordEncoderGateway gateway;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        gateway = new PasswordEncoderGateway(passwordEncoderDataSource);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldEncode() {
        // Arrange
        var password = "123";
        var encodedPassword = "*((¨¨";

        when(passwordEncoderDataSource.encode(password)).thenReturn(encodedPassword);

        // Act
        var response = gateway.encode(password);

        // Assert
        assertEquals(response, encodedPassword);
    }

    @Test
    void shouldMatches() {
        // Arrange
        var password = "123";
        var encodedPassword = "*((¨¨";

        when(passwordEncoderDataSource.matches(password, encodedPassword)).thenReturn(true);

        // Act
        assertDoesNotThrow(() -> gateway.matches(password, encodedPassword));
    }

    @Test
    void shouldThrowsWhenNotMatches() {
        // Arrange
        var password = "123";
        var encodedPassword = "*((¨¨";

        when(passwordEncoderDataSource.matches(password, encodedPassword)).thenReturn(false);

        // Act
        assertThrows(BadCredentialsException.class, () -> gateway.matches(password, encodedPassword));
    }
}
