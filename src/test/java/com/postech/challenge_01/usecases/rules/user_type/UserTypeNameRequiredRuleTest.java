package com.postech.challenge_01.usecases.rules.user_type;

import com.postech.challenge_01.builder.user_type.UserTypeBuilder;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.domain.enums.UserTypeEnum;
import com.postech.challenge_01.exceptions.UserTypeNameRequiredException;
import com.postech.challenge_01.application.usecases.rules.user_type.UserTypeNameRequiredRule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTypeNameRequiredRuleTest {

    private final UserTypeNameRequiredRule rule = new UserTypeNameRequiredRule();

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        UserType userType = mock(UserType.class);
        when(userType.getName()).thenReturn(null);

        assertThatThrownBy(() -> rule.execute(userType))
                .isInstanceOf(UserTypeNameRequiredException.class)
                .hasMessage("O nome do tipo de usuário é obrigatório");
    }


    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        UserType userType = new UserType(null, " ", UserTypeEnum.Owner);

        assertThatThrownBy(() -> rule.execute(userType))
                .isInstanceOf(UserTypeNameRequiredException.class)
                .hasMessage("O nome do tipo de usuário é obrigatório");
    }

    @Test
    void shouldPassWhenNameIsValid() {
        UserType userType = UserTypeBuilder
                .oneUserType()
                .withName("Admin")
                .build();

        rule.execute(userType);
    }
}
