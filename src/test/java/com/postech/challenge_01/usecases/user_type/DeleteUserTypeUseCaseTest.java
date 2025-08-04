package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.application.usecases.user_type.DeleteUserTypeUseCase;
import com.postech.challenge_01.application.usecases.user_type.SaveUserTypeUseCase;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteUserTypeUseCaseTest {
    @Mock
    private IUserTypeGateway gateway;

    @Mock
    private Rule<UserType> ruleMock;

    @InjectMocks
    private DeleteUserTypeUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        useCase = new DeleteUserTypeUseCase(gateway, List.of(ruleMock));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldDeleteUserTypeSuccessfully() {
        Long id = 1L;

        doNothing().when(gateway).delete(id);

        useCase.execute(id);

        verify(gateway).delete(id);
    }

    @Test
    void shouldThrowWhenUserTypeNotFound() {
        Long id = 1L;

        doThrow(UserTypeNotFoundException.class).when(gateway).delete(id);

        assertThrows(UserTypeNotFoundException.class, () -> useCase.execute(id));

        verify(gateway).delete(id);
    }
}
