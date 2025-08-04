package com.postech.challenge_01.usecases.rules.user;

import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.rules.user.DuplicateLoginRule;
import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class DuplicateLoginRuleTest {

    @Mock
    private IUserGateway userGateway;

    @InjectMocks
    private DuplicateLoginRule role;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        role = new DuplicateLoginRule(userGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldPassWhenUserEmailIsUnique() {
        // Arrange
        Long id = 1L;
        String login = "teste.teste";

        var user = UserBuilder.oneUser()
                .withId(id)
                .withLogin(login)
                .build();

        when(userGateway.findByLogin(login)).thenReturn(Optional.of(user));

        // Act
        role.execute(user);
    }

    @Test
    void shouldPassWhenUserEmailIsEmpty() {
        // Arrange
        Long id = 1L;
        String login = "teste.teste";

        var user = UserBuilder.oneUser()
                .withId(id)
                .withLogin(login)
                .build();

        when(userGateway.findByLogin(login)).thenReturn(Optional.empty());

        // Act + Assert
        assertDoesNotThrow(() -> role.execute(user));
    }

    @Test
    void shouldNotPassWhenUserEmailIsNotUnique() {
        // Arrange
        Long id = 1L;
        String login = "teste.teste";

        var user = UserBuilder.oneUser()
                .withId(id)
                .withLogin(login)
                .build();

        var otherUser = UserBuilder.oneUser()
                .withId(2L)
                .withLogin(login)
                .build();

        when(userGateway.findByLogin(login)).thenReturn(Optional.of(otherUser));

        // Act + Assert
        assertThrows(UserAlreadyExistsException.class, () -> role.execute(user));
    }
}
