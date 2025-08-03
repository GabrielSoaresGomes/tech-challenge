package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.application.usecases.address.FindAddressByIdUseCase;
import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FindAddressByIdUseCaseTest {

    @Mock
    private IAddressGateway gateway;

    @InjectMocks
    private FindAddressByIdUseCase useCase;

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
    void shouldReturnAddressSuccessfully() {
        Long id = 1L;
        LocalDateTime lastModifiedDateTime = LocalDateTime.now();
        Address expectedResponse = AddressBuilder
                .oneAddress()
                .withId(id)
                .withStreet("Rua Teste")
                .withCreatedAt(lastModifiedDateTime)
                .build();

        Address address = AddressBuilder
                .oneAddress()
                .withId(id)
                .withStreet("Rua Teste")
                .withCreatedAt(lastModifiedDateTime)
                .build();

        when(gateway.findById(anyLong())).thenReturn(address);

        Address response = useCase.execute(id);

        verify(gateway, times(1)).findById(anyLong());

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(expectedResponse);
        assertThat(response.getStreet()).isEqualTo("Rua Teste");
}

    @Test
    void shouldThrowWhenAddressNotFound() {
        Long id = 1L;

        doThrow(ResourceNotFoundException.class).when(gateway).findById(anyLong());

        assertThrows(ResourceNotFoundException.class, () -> useCase.execute(id));

        verify(gateway).findById(id);
    }
}
