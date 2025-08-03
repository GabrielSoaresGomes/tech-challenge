package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.builder.address.AddressDTOBuilder;
import com.postech.challenge_01.exceptions.AddressNotFoundException;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.AddressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressGatewayTest {
    @Mock
    private AddressRepository repository;

    @InjectMocks
    private AddressGateway gateway;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        gateway = new AddressGateway(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldFindById() {
        // Arrange
        var id = 1L;
        var dto = AddressDTOBuilder.oneAddressDTO()
                .withId(id).build();

        when(repository.findById(id)).thenReturn(Optional.ofNullable(dto));

        // Act
        var response = gateway.findById(id);

        assertEquals(response.getId(), id);
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        // Arrange
        var id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act + Arrange
        assertThrows(ResourceNotFoundException.class, () -> gateway.findById(id));
    }

    @Test
    void shouldFindAll() {
        // Arrange
        var dto1 = AddressDTOBuilder.oneAddressDTO().build();
        var dto2 = AddressDTOBuilder.oneAddressDTO().build();
        var dto3 = AddressDTOBuilder.oneAddressDTO().build();
        var dtoList = List.of(dto1, dto2, dto3);

        when(repository.findAll(anyInt(), anyLong())).thenReturn(dtoList);

        // Act
        var result = gateway.findAll(PageRequest.of(0, 10));

        // Assert
        assertEquals(3, result.size());
    }

    @Test
    void shouldSave() {
        // Arrange
        var entity = AddressBuilder.oneAddress().build();
        var dto = AddressDTOBuilder.oneAddressDTO().build();

        when(repository.save(any())).thenReturn(dto);

        // Act
        var result = gateway.save(entity);

        // Assert
        assertNotNull(result.getId());
        assertEquals(result.getPostalCode(), dto.postalCode());
    }

    @Test
    void shouldUpdateWhenEntityExist() {
        // Arrange
        var id = 1L;
        var entity = AddressBuilder.oneAddress().build();
        var dto = AddressDTOBuilder.oneAddressDTO().build();

        when(repository.update(any())).thenReturn(Optional.ofNullable(dto));

        // Act
        var result = gateway.update(entity, id);

        // Assert
        assertEquals(result.getPostalCode(), dto.postalCode());
    }

    @Test
    void shouldThrowUpdateWhenEntityNotExist() {
        // Arrange
        var id = 1L;
        var entity = AddressBuilder.oneAddress().build();
        when(repository.update(any())).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(AddressNotFoundException.class, () -> gateway.update(entity, id));
    }

    @Test
    void shouldDeleteWhenUserExist() {
        // Arrange
        var id = 1L;
        when(repository.delete(id)).thenReturn(1);

        // Act + Assert
        assertDoesNotThrow(() -> gateway.delete(id));
    }

    @Test
    void shouldThrowDeleteWhenEntityNotExist() {
        // Arrange
        var id = 1L;
        when(repository.delete(id)).thenReturn(0);

        // Act + Assert
        assertThrows(AddressNotFoundException.class, () -> gateway.delete(id));
    }

    @Test
    void shouldFindAllByUserId() {
        // Arrange
        var userId = 1L;
        var dto1 = AddressDTOBuilder.oneAddressDTO().build();
        var dto2 = AddressDTOBuilder.oneAddressDTO().build();
        var dto3 = AddressDTOBuilder.oneAddressDTO().build();
        var dtoList = List.of(dto1, dto2, dto3);
        var pageRequest = PageRequest.of(0, 10);
        when(repository.findAllByUserId(any(), anyInt(), anyLong())).thenReturn(dtoList);

        // Act
        var result = gateway.findAllByUserId(userId, pageRequest.getPageSize(), pageRequest.getOffset());

        // Assert
        assertEquals(3, result.size());
    }

    @Test
    void shouldDeleteByUserId() {
        // Arrange
        var userId = 1L;

        //Act + Assert
        assertDoesNotThrow(() -> gateway.deleteByUserId(userId));
    }

    @Test
    void shouldDeleteByRestaurantWhenRestaurantExist() {
        // Arrange
        var id = 1L;
        when(repository.deleteByRestaurantId(id)).thenReturn(1);

        // Act + Assert
        assertDoesNotThrow(() -> gateway.deleteByRestaurantId(id));
    }

    @Test
    void shouldThrowsDeleteByRestaurantWhenRestaurantNotExist() {
        // Arrange
        var id = 1L;
        when(repository.deleteByRestaurantId(id)).thenReturn(0);

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> gateway.deleteByRestaurantId(id));
    }
}
