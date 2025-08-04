package com.postech.challenge_01.usecases.rules.user_type;

import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.rules.user_type.DuplicateUserTypeNameRule;
import com.postech.challenge_01.application.usecases.rules.user_type.UserTypeIsUsedRule;
import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.builder.user_type.UserTypeBuilder;
import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.exceptions.UserTypeAlreadyExistsException;
import com.postech.challenge_01.exceptions.UserTypeIsUsedException;
import com.postech.challenge_01.interface_adapter.gateways.UserGateway;
import com.postech.challenge_01.interface_adapter.gateways.UserTypeGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class UserTypeIsUsedRuleTest {

    private final IUserGateway gateway = mock(IUserGateway.class);
    private UserTypeIsUsedRule rule;

    @BeforeEach
    void setUp() {
        rule = new UserTypeIsUsedRule(gateway);
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExistsAndDifferentId() {
        UserType deletingUserType = UserTypeBuilder
                .oneUserType()
                .withId(1L)
                .withName("Admin")
                .build();

        User user = UserBuilder.oneUser().build();

        when(gateway.findByUserTypeId(1L)).thenReturn(List.of(user));

        assertThatThrownBy(() -> rule.execute(deletingUserType))
                .isInstanceOf(UserTypeIsUsedException.class)
                .hasMessage("Erro ao apagar tipo '" + deletingUserType.getName() + "', existe usuários vinculados a ele.");

        verify(gateway, times(1)).findByUserTypeId(1L);
    }

    @Test
    void shouldNotThrowExceptionWhenNoUsersAreLinked() {
        UserType deletingUserType = UserTypeBuilder
                .oneUserType()
                .withId(1L)
                .withName("Admin")
                .build();

        when(gateway.findByUserTypeId(1L)).thenReturn(List.of());

        rule.execute(deletingUserType);

        verify(gateway, times(1)).findByUserTypeId(1L);
    }
}
