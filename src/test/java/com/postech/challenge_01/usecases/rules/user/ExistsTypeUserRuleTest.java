package com.postech.challenge_01.usecases.rules.user;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.rules.user.ExistsTypeUserRule;
import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.builder.user_type.UserTypeBuilder;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ExistsTypeUserRuleTest {

    @Mock
    private IUserTypeGateway userTypeGateway;

    @InjectMocks
    private ExistsTypeUserRule role;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        role = new ExistsTypeUserRule(userTypeGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldPassWhenUserTypeExists() {
        // Arrange
        Long id = 1L;

        var user = UserBuilder.oneUser()
                .withId(id)
                .build();

        var userType = UserTypeBuilder.oneUserType()
                .build();

        when(userTypeGateway.findById(id)).thenReturn(userType);

        // Act + Assert
        assertDoesNotThrow(() -> role.execute(user));
    }

    @Test
    void shouldNotPassWhenUserTypeNotExists() {
        // Arrange
        Long id = 1L;

        var user = UserBuilder.oneUser()
                .withId(id)
                .build();

        when(userTypeGateway.findById(id)).thenThrow(new ResourceNotFoundException("Tipo de usuário não encontrado para o id " + id));

        // Act + Assert
        assertThrows(UserTypeNotFoundException.class, () -> role.execute(user));
    }
}
