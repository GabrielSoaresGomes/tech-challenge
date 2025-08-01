package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.dtos.requests.address.FindAllAddressesByUserIdRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.repositories.address.AddressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class FindAllAddressesByUserIdUseCaseTest {

    private AutoCloseable closeable;

    @Mock
    private AddressRepository addressRepository;

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

        when(addressRepository.findAllByUserId(userId, pageable.getPageSize(), pageable.getOffset()))
                .thenReturn(entityList);

        List<AddressResponseDTO> result = findAllAddressesByUserIdUseCase.execute(request);

        verify(addressRepository, times(1))
                .findAllByUserId(userId, pageable.getPageSize(), pageable.getOffset());

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).street()).isEqualTo("Rua A");
        assertThat(result.get(1).street()).isEqualTo("Rua B");
    }

    @Test
    void shouldReturnEmptyListWhenNoAddressesFound() {
        Long userId = 1L;
        Pageable pageable = PageRequest.of(0, 2);
        FindAllAddressesByUserIdRequestDTO request = new FindAllAddressesByUserIdRequestDTO(pageable, userId);

        when(addressRepository.findAllByUserId(userId, pageable.getPageSize(), pageable.getOffset()))
                .thenReturn(List.of());

        List<AddressResponseDTO> result = findAllAddressesByUserIdUseCase.execute(request);

        verify(addressRepository, times(1))
                .findAllByUserId(userId, pageable.getPageSize(), pageable.getOffset());

        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
