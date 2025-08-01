package com.postech.challenge_01.usecases.rules.user_type;

import com.postech.challenge_01.builder.UserTypeBuilder;
import com.postech.challenge_01.exceptions.UserTypeAlreadyExistsException;
import com.postech.challenge_01.repositories.user_type.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class DuplicateUserTypeNameRuleTest {

    private final UserTypeRepository userTypeRepository = mock(UserTypeRepository.class);
    private DuplicateUserTypeNameRule rule;

    @BeforeEach
    void setUp() {
        rule = new DuplicateUserTypeNameRule(userTypeRepository);
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExistsAndDifferentId() {
        var existingUserType = UserTypeBuilder
                .oneUserType()
                .withId(2L)
                .withName("Admin")
                .build();

        var newUserType = UserTypeBuilder
                .oneUserType()
                .withId(1L)
                .withName("Admin")
                .build();

        when(userTypeRepository.findByName("Admin")).thenReturn(Optional.of(existingUserType));

        assertThatThrownBy(() -> rule.execute(newUserType))
                .isInstanceOf(UserTypeAlreadyExistsException.class)
                .hasMessage("Tipo de usuário já existente com nome: " + newUserType.getName());
    }

    @Test
    void shouldNotThrowWhenNoUserTypeWithSameNameExists() {
        var userType = UserTypeBuilder
                .oneUserType()
                .withId(1L)
                .withName("Admin")
                .build();

        when(userTypeRepository.findByName("Admin")).thenReturn(Optional.empty());

        rule.execute(userType);
    }

    @Test
    void shouldNotThrowWhenSameIdExists() {
        var userType = UserTypeBuilder
                .oneUserType()
                .withId(1L)
                .withName("Admin")
                .build();

        var sameUserType = UserTypeBuilder
                .oneUserType()
                .withId(1L)
                .withName("Admin")
                .build();

        when(userTypeRepository.findByName("Admin")).thenReturn(Optional.of(sameUserType));

        rule.execute(userType);
    }
}
