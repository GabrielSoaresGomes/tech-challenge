package com.postech.challenge_01.usecases.rules.user;

import com.postech.challenge_01.application.usecases.rules.user.InvalidEmailRule;
import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.exceptions.InvalidEmailException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvalidEmailRuleTest {

    @InjectMocks
    private InvalidEmailRule role;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        role = new InvalidEmailRule();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldPassWhenEmailIsValid1() {
        // Arrange
        String email = "usuario@dominio.com";

        var user = UserBuilder.oneUser()
                .withEmail(email)
                .build();

        // Act + Assert
        assertDoesNotThrow(() -> role.execute(user));
    }

    @Test
    void shouldPassWhenEmailIsValid2() {
        // Arrange
        String email = "user.name@sub.dominio.co";

        var user = UserBuilder.oneUser()
                .withEmail(email)
                .build();

        // Act + Assert
        assertDoesNotThrow(() -> role.execute(user));
    }

    @Test
    void shouldPassWhenEmailIsValid3() {
        // Arrange
        String email = "u@d.io";

        var user = UserBuilder.oneUser()
                .withEmail(email)
                .build();

        // Act + Assert
        assertDoesNotThrow(() -> role.execute(user));
    }

    @Test
    void shouldPassWhenEmailIsNull() {
        // Arrange
        String email = null;

        var user = UserBuilder.oneUser()
                .withEmail(email)
                .build();

        // Act + Assert
        assertDoesNotThrow(() -> role.execute(user));
    }

    @Test
    void shouldNotPassWhenEmailIsNotValid1() {
        // Arrange
        String email = "usuario@.com";

        var user = UserBuilder.oneUser()
                .withEmail(email)
                .build();

        // Act + Assert
        assertThrows(InvalidEmailException.class, () -> role.execute(user));
    }

    @Test
    void shouldNotPassWhenEmailIsNotValid2() {
        // Arrange
        String email = "user name@dominio.com";

        var user = UserBuilder.oneUser()
                .withEmail(email)
                .build();

        // Act + Assert
        assertThrows(InvalidEmailException.class, () -> role.execute(user));
    }

    @Test
    void shouldNotPassWhenEmailIsNotValid3() {
        // Arrange
        String email = "@dominio.com";

        var user = UserBuilder.oneUser()
                .withEmail(email)
                .build();

        // Act + Assert
        assertThrows(InvalidEmailException.class, () -> role.execute(user));
    }
}
