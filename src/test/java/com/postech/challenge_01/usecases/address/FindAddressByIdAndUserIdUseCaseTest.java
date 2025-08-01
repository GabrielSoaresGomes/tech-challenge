package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.dtos.requests.address.FindAddressRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.repositories.address.AddressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FindAddressByIdAndUserIdUseCaseTest {

    private AutoCloseable closeable;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private FindAddressByIdAndUserIdUseCase findAddressByIdAndUserIdUseCase;

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
        Long addressId = 1L;
        Long userId = 2L;
        FindAddressRequestDTO request = new FindAddressRequestDTO(userId, addressId);

        Address address = AddressBuilder
                .oneAddress()
                .withId(addressId)
                .withCreatedAt(LocalDateTime.now())
                .build();

        when(addressRepository.findByIdAndUserId(userId, addressId))
                .thenReturn(Optional.of(address));

        AddressResponseDTO response = findAddressByIdAndUserIdUseCase.execute(request);

        verify(addressRepository, times(1)).findByIdAndUserId(userId, addressId);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(addressId);
        assertThat(response.street()).isEqualTo(address.getStreet());
    }

    @Test
    void shouldThrowWhenAddressNotFound() {
        Long addressId = 1L;
        Long userId = 1L;
        FindAddressRequestDTO request = new FindAddressRequestDTO(userId, addressId);

        when(addressRepository.findByIdAndUserId(userId, addressId))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> findAddressByIdAndUserIdUseCase.execute(request));

        verify(addressRepository, times(1)).findByIdAndUserId(userId, addressId);
    }
}
