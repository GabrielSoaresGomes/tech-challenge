package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.repositories.restaurant.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DeleteRestaurantUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private DeleteRestaurantUseCase deleteRestaurantUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        deleteRestaurantUseCase = new DeleteRestaurantUseCase(restaurantRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldExecuteAndDeleteRestaurantById() {
        // Arrange
        Long restaurantId = 1L;

        when(restaurantRepository.delete(anyLong())).thenReturn(1);

        // Act
        deleteRestaurantUseCase.execute(restaurantId);

        // Assert
        verify(restaurantRepository, times(1)).delete(restaurantId);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenRestaurantNotFound() {
        // Arrange
        Long restaurantId = 1L;
        when(restaurantRepository.delete(restaurantId)).thenReturn(0);

        // Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> deleteRestaurantUseCase.execute(restaurantId));

        assertThat(exception.getMessage()).isEqualTo("Restaurante com ID " + restaurantId + " não foi encontrado");
        verify(restaurantRepository, times(1)).delete(restaurantId);
    }
}
