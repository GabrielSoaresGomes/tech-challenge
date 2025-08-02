package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.user_type.DeleteUserTypeUseCase;
import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteUserTypeUseCaseTest {
    @Mock
    private IUserTypeGateway gateway;

    @InjectMocks
    private DeleteUserTypeUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
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
