package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.usecases.address.FindAddressByIdAndUserIdUseCase;
import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.requests.address.FindAddressRequestDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class FindAddressByIdAndUserIdUseCaseTest {

    private AutoCloseable closeable;

    @Mock
    private IAddressGateway addressGateway;

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

        //Arrange
        Long addressId = 1L;
        Long userId = 2L;
        FindAddressRequestDTO request = new FindAddressRequestDTO(userId, addressId);

        Address address = AddressBuilder
                .oneAddress()
                .withId(addressId)
                .withCreatedAt(LocalDateTime.now())
                .build();

        when(addressGateway.findById(addressId))
                .thenReturn(address);

        //Act
        var response = findAddressByIdAndUserIdUseCase.execute(request);

        //Assert
        verify(addressGateway, times(1)).findById(addressId);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(addressId);
        assertThat(response.getStreet()).isEqualTo(address.getStreet());
    }
}
