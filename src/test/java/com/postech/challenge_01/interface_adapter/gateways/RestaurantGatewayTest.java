package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.builder.restaurant.RestaurantDTOBuilder;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.exceptions.RestaurantNotFoundException;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.RestaurantRepository;
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

public class RestaurantGatewayTest {
    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private RestaurantGateway gateway;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        gateway = new RestaurantGateway(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldFindById() {
        // Arrange
        var id = 1L;
        var dto = RestaurantDTOBuilder.oneRestaurantDTO()
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
        var dto1 = RestaurantDTOBuilder.oneRestaurantDTO().build();
        var dto2 = RestaurantDTOBuilder.oneRestaurantDTO().build();
        var dto3 = RestaurantDTOBuilder.oneRestaurantDTO().build();
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
        var entity = RestaurantBuilder.oneRestaurant().build();
        var dto = RestaurantDTOBuilder.oneRestaurantDTO().build();

        when(repository.save(any())).thenReturn(dto);

        // Act
        var result = gateway.save(entity);

        // Assert
        assertNotNull(result.getId());
        assertEquals(result.getName(), dto.name());
    }

    @Test
    void shouldUpdateWhenEntityExist() {
        // Arrange
        var id = 1L;
        var entity = RestaurantBuilder.oneRestaurant().build();
        var dto = RestaurantDTOBuilder.oneRestaurantDTO().build();

        when(repository.update(any())).thenReturn(Optional.ofNullable(dto));

        // Act
        var result = gateway.update(entity, id);

        // Assert
        assertEquals(result.getName(), dto.name());
    }

    @Test
    void shouldThrowUpdateWhenEntityNotExist() {
        // Arrange
        var id = 1L;
        var entity = RestaurantBuilder.oneRestaurant().build();
        when(repository.update(any())).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RestaurantNotFoundException.class, () -> gateway.update(entity, id));
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
        assertThrows(RestaurantNotFoundException.class, () -> gateway.delete(id));
    }

    @Test
    void shouldFindAllOpened() {
        // Arrange
        var dto1 = RestaurantDTOBuilder.oneRestaurantDTO().build();
        var dto2 = RestaurantDTOBuilder.oneRestaurantDTO().build();
        var dto3 = RestaurantDTOBuilder.oneRestaurantDTO().build();
        var dtoList = List.of(dto1, dto2, dto3);

        when(repository.findAllOpen(anyInt(), anyLong())).thenReturn(dtoList);

        // Act
        var result = gateway.findAllOpen(PageRequest.of(0, 10));

        // Assert
        assertEquals(3, result.size());
    }
}
