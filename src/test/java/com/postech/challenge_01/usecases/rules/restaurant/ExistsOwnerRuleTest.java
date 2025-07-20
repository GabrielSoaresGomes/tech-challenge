package com.postech.challenge_01.usecases.rules.restaurant;

import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import com.postech.challenge_01.repositories.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExistsOwnerRuleTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExistsOwnerRule existsOwnerRule;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        existsOwnerRule = new ExistsOwnerRule(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldPassWhenUserExists() {
        // Arrange
        Long ownerId = 1L;
        Restaurant restaurant = RestaurantBuilder.oneRestaurant().withOwnerId(ownerId).build();

        when(userRepository.findById(ownerId)).thenReturn(Optional.of(mock())); // usuário qualquer

        // Act + Assert
        existsOwnerRule.execute(restaurant);

        verify(userRepository, times(1)).findById(ownerId);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
        // Arrange
        Long ownerId = 1L;
        Restaurant restaurant = RestaurantBuilder.oneRestaurant().withOwnerId(ownerId).build();


        when(userRepository.findById(ownerId)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(UserNotFoundException.class, () -> existsOwnerRule.execute(restaurant));
        verify(userRepository, times(1)).findById(ownerId);
    }
}
