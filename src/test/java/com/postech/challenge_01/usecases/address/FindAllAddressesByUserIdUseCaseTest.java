package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.usecases.address.FindAllAddressesByUserIdUseCase;
import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.requests.address.FindAllAddressesByUserIdRequestDTO;
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
import static org.mockito.Mockito.*;

public class FindAllAddressesByUserIdUseCaseTest {

    private AutoCloseable closeable;

    @Mock
    private IAddressGateway addressGateway;

    @InjectMocks
    private FindAllAddressesByUserIdUseCase findAllAddressesByUserIdUseCase;

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
        Long userId = 1L;
        Pageable pageable = PageRequest.of(0, 2);
        FindAllAddressesByUserIdRequestDTO request = new FindAllAddressesByUserIdRequestDTO(pageable, userId);

        List<Address> entityList = List.of(
                AddressBuilder.oneAddress().withId(1L).withStreet("Rua A").withCreatedAt(LocalDateTime.now()).build(),
                AddressBuilder.oneAddress().withId(2L).withStreet("Rua B").withCreatedAt(LocalDateTime.now()).build()
        );

        when(addressGateway.findAllByUserId(userId, pageable.getPageSize(), pageable.getOffset()))
                .thenReturn(entityList);

        var result = findAllAddressesByUserIdUseCase.execute(request);

        verify(addressGateway, times(1))
                .findAllByUserId(userId, pageable.getPageSize(), pageable.getOffset());

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getStreet()).isEqualTo("Rua A");
        assertThat(result.get(1).getStreet()).isEqualTo("Rua B");
    }

    @Test
    void shouldReturnEmptyListWhenNoAddressesFound() {
        Long userId = 1L;
        Pageable pageable = PageRequest.of(0, 2);
        FindAllAddressesByUserIdRequestDTO request = new FindAllAddressesByUserIdRequestDTO(pageable, userId);

        when(addressGateway.findAllByUserId(userId, pageable.getPageSize(), pageable.getOffset()))
                .thenReturn(List.of());

        var result = findAllAddressesByUserIdUseCase.execute(request);

        verify(addressGateway, times(1))
                .findAllByUserId(userId, pageable.getPageSize(), pageable.getOffset());

        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
