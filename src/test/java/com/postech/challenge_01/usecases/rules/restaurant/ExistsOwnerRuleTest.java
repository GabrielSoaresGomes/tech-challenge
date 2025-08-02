package com.postech.challenge_01.usecases.rules.restaurant;

import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.application.usecases.rules.restaurant.ExistsOwnerRule;
import com.postech.challenge_01.interface_adapter.gateways.UserGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;

public class ExistsOwnerRuleTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private ExistsOwnerRule existsOwnerRule;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        existsOwnerRule = new ExistsOwnerRule(userGateway);
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

        when(userGateway.findById(ownerId)).thenReturn(mock(User.class));

        // Act + Assert
        existsOwnerRule.execute(restaurant);

        verify(userGateway, times(1)).findById(ownerId);
    }
}
