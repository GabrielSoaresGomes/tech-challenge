package com.postech.challenge_01.usecases.rules.user_type;

import com.postech.challenge_01.application.usecases.rules.user_type.DuplicateUserTypeNameRule;
import com.postech.challenge_01.builder.user_type.UserTypeBuilder;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.exceptions.UserTypeAlreadyExistsException;
import com.postech.challenge_01.interface_adapter.gateways.UserTypeGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class DuplicateUserTypeNameRuleTest {

    private final UserTypeGateway gateway = mock(UserTypeGateway.class);
    private DuplicateUserTypeNameRule rule;

    @BeforeEach
    void setUp() {
        rule = new DuplicateUserTypeNameRule(gateway);
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExistsAndDifferentId() {
        UserType existingUserType = UserTypeBuilder
                .oneUserType()
                .withId(2L)
                .withName("Admin")
                .build();

        UserType newUserType = UserTypeBuilder
                .oneUserType()
                .withId(1L)
                .withName("Admin")
                .build();

        when(gateway.findByName("Admin")).thenReturn(Optional.of(existingUserType));

        assertThatThrownBy(() -> rule.execute(newUserType))
                .isInstanceOf(UserTypeAlreadyExistsException.class)
                .hasMessage("Tipo de usuário já existente com nome: " + newUserType.getName());

        verify(gateway, times(1)).findByName("Admin");
    }

    @Test
    void shouldNotThrowWhenNoUserTypeWithSameNameExists() {
        UserType userType = UserTypeBuilder
                .oneUserType()
                .withId(1L)
                .withName("Admin")
                .build();

        when(gateway.findByName("Admin")).thenReturn(Optional.empty());

        rule.execute(userType);

        verify(gateway, times(1)).findByName("Admin");
    }

    @Test
    void shouldNotThrowWhenSameIdExists() {
        UserType userType = UserTypeBuilder
                .oneUserType()
                .withId(1L)
                .withName("Admin")
                .build();

        UserType sameUserType = UserTypeBuilder
                .oneUserType()
                .withId(1L)
                .withName("Admin")
                .build();

        when(gateway.findByName("Admin")).thenReturn(Optional.of(sameUserType));

        rule.execute(userType);

        verify(gateway, times(1)).findByName("Admin");
    }
}
