package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.application.usecases.address.SaveAddressUseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.builder.address.AddressRequestDTOBuilder;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaveAddressUseCaseTest {

    @Mock
    private IAddressGateway gateway;

    @Mock
    private Rule<Address> ruleMock;

    @InjectMocks
    private SaveAddressUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        useCase = new SaveAddressUseCase(gateway, List.of(ruleMock));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldExecuteAndSaveAddressSuccessfully() {
        Long id = 1L;

        AddressRequestDTO requestDTO = AddressRequestDTOBuilder
                .oneAddressRequestDTO()
                .build();

        Address savedAddress = AddressBuilder
                .oneAddress()
                .withId(id)
                .withStreet(requestDTO.street())
                .withNumber(requestDTO.number())
                .withComplement(requestDTO.complement())
                .withNeighborhood(requestDTO.neighborhood())
                .withCity(requestDTO.city())
                .withState(requestDTO.state())
                .withCountry(requestDTO.country())
                .withPostalCode(requestDTO.postalCode())
                .withCreatedAt(LocalDateTime.now())
                .build();

        when(gateway.save(any(Address.class))).thenReturn(savedAddress);

        Address response = useCase.execute(requestDTO);

        verify(ruleMock).execute(any(Address.class));
        verify(gateway, times(1)).save(any(Address.class));

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getStreet()).isEqualTo(requestDTO.street());
        assertThat(response.getNumber()).isEqualTo(requestDTO.number());
        assertThat(response.getComplement()).isEqualTo(requestDTO.complement());
        assertThat(response.getNeighborhood()).isEqualTo(requestDTO.neighborhood());
        assertThat(response.getCity()).isEqualTo(requestDTO.city());
        assertThat(response.getState()).isEqualTo(requestDTO.state());
        assertThat(response.getCountry()).isEqualTo(requestDTO.country());
        assertThat(response.getPostalCode()).isEqualTo(requestDTO.postalCode());
    }

    @Test
    void shouldThrowInvalidRule() {
        AddressRequestDTO requestDTO = AddressRequestDTOBuilder
                .oneAddressRequestDTO()
                .build();

        doThrow(new RuntimeException("Rule")).when(ruleMock).execute(any(Address.class));

        assertThrows(RuntimeException.class, () -> useCase.execute(requestDTO));
        verify(ruleMock).execute(any(Address.class));
        verify(gateway, never()).save(any(Address.class));
    }
}
