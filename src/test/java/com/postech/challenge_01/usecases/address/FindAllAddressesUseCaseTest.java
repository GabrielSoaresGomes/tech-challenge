package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.application.usecases.address.FindAllAddressesUseCase;
import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FindAllAddressesUseCaseTest {

    @Mock
    private IAddressGateway gateway;

    @InjectMocks
    private FindAllAddressesUseCase useCase;

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
    void shouldReturnListOfAddresses() {
        Pageable pageable = PageRequest.of(0, 10);

        Address addres1 = AddressBuilder.oneAddress().withId(1L).withStreet("Rua A").withCreatedAt(LocalDateTime.now()).build();
        Address addres2 = AddressBuilder.oneAddress().withId(2L).withStreet("Rua B").withCreatedAt(LocalDateTime.now()).build();

        List<Address> addresses = List.of(addres1, addres2);

        when(gateway.findAll(pageable)).thenReturn(addresses);

        List<Address> result = useCase.execute(pageable);

        verify(gateway, times(1)).findAll(pageable);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getStreet()).isEqualTo("Rua A");
        assertThat(result.get(1).getStreet()).isEqualTo("Rua B");
    }

    @Test
    void shouldReturnEmptyListWhenNoAddressesExist() {
        Pageable pageable = PageRequest.of(0, 2);

        when(gateway.findAll(pageable)).thenThrow(new RuntimeException("Erro no gateway"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.execute(pageable));

        assertEquals("Erro no gateway", ex.getMessage());

        verify(gateway, times(1)).findAll(pageable);
    }
}
