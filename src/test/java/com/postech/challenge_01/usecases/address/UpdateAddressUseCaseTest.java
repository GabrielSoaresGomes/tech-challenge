package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.application.mappers.AddressMapper;
import com.postech.challenge_01.application.usecases.address.UpdateAddressUseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.builder.address.AddressUpdateRequestDTOBuilder;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.requests.address.AddressUpdateRequestDTO;
import com.postech.challenge_01.exceptions.AddressNotFoundException;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateAddressUseCaseTest {

    @Mock
    private IAddressGateway gateway;

    @Mock
    private Rule<Address> ruleMock;

    @InjectMocks
    private UpdateAddressUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        useCase = new UpdateAddressUseCase(gateway, List.of(ruleMock));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldUpdateAddressSuccessfully() {
        Long id = 1L;
        AddressUpdateRequestDTO request = AddressUpdateRequestDTOBuilder
                .oneAddressUpdateRequestDTO()
                .build();

        Address updatedAddress = AddressBuilder
                .oneAddress()
                .withId(id)
                .build();

        Address expectedResponse = AddressBuilder
                .oneAddress()
                .withId(id)
                .build();

        when(gateway.update(any(Address.class), anyLong())).thenReturn(updatedAddress);

        Address response = useCase.execute(request);

        verify(gateway, times(1)).update(any(Address.class), eq(id));
        verify(ruleMock).execute(any(Address.class));

        assertEquals(expectedResponse, response);
        assertThat(response.getId()).isEqualTo(id);
    }

    @Test
    void shouldThrowInvalidRule() {
        Long id = 1L;
        AddressUpdateRequestDTO requestDTO = AddressUpdateRequestDTOBuilder
                .oneAddressUpdateRequestDTO()
                .withId(id)
                .build();

        Address address = AddressBuilder
                .oneAddress()
                .withId(id)
                .build();

        try (MockedStatic<AddressMapper> mapper = mockStatic(AddressMapper.class)) {
            mapper.when(() -> AddressMapper.toAddress(id, requestDTO.data()))
                    .thenReturn(address);

            doThrow(new RuntimeException("Falha na regra"))
                    .when(ruleMock).execute(address);

            RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.execute(requestDTO));

            verify(ruleMock, times(1)).execute(address);
            verify(gateway, never()).update(any(), anyLong());

            assertEquals("Falha na regra", ex.getMessage());
        }
    }

    @Test
    void shouldThrowWhenAddressNotFound() {
        Long id = 1L;
        AddressUpdateRequestDTO requestDTO = AddressUpdateRequestDTOBuilder
                .oneAddressUpdateRequestDTO()
                .withId(id)
                .build();

        Address address = AddressBuilder
                .oneAddress()
                .withId(id)
                .build();

        try (MockedStatic<AddressMapper> mapper = mockStatic(AddressMapper.class)) {
            mapper.when(() -> AddressMapper.toAddress(id, requestDTO.data()))
                    .thenReturn(address);

            when(gateway.update(address, id)).thenThrow(new AddressNotFoundException(id));

            AddressNotFoundException ex = assertThrows(AddressNotFoundException.class, () -> useCase.execute(requestDTO));

            verify(ruleMock, times(1)).execute(address);
            verify(gateway, times(1)).update(address, id);

            assertTrue(ex.getMessage().contains(id.toString()));
        }
    }
}
