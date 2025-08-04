package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.domain.UserAddress;
import com.postech.challenge_01.dtos.transfer.user_address.UserAddressDTO;
import com.postech.challenge_01.infrastructure.data_sources.repositories.user_address.UserAddressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class UserAddressGatewayTest {
    @Mock
    private UserAddressRepository repository;

    @InjectMocks
    private UserAddressGateway gateway;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        gateway = new UserAddressGateway(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldSave() {
        // Arrange
        var userId = 1L;
        var addressId = 1L;
        var entity = new UserAddress(userId, addressId);
        var dto = new UserAddressDTO(1L, userId, addressId);

        when(repository.save(any())).thenReturn(dto);

        // Act
        var result = gateway.save(entity);

        // Assert
        assertNotNull(result.getId());
        assertEquals(result.getUserId(), dto.userId());
        assertEquals(result.getAddressId(), dto.addressId());
    }
}
