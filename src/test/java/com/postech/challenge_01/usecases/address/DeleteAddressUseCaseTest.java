package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.application.usecases.address.DeleteAddressUseCase;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteAddressUseCaseTest {

    @Mock
    private IAddressGateway gateway;

    @InjectMocks
    private DeleteAddressUseCase useCase;

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
    void shouldDeleteAddressSuccessfully() {
        Long id = 1L;

        doNothing().when(gateway).delete(id);

        useCase.execute(id);

        verify(gateway).delete(id);
    }

    @Test
    void shouldThrowWhenAddressNotFound() {
        Long id = 1L;

        doThrow(ResourceNotFoundException.class).when(gateway).delete(id);

        assertThrows(ResourceNotFoundException.class, () -> useCase.execute(id));

        verify(gateway).delete(id);
    }
}
