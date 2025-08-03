package com.postech.challenge_01.usecases.rules.restaurant;

import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.rules.restaurant.UserIsOwnerRule;
import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.builder.user_type.UserTypeBuilder;
import com.postech.challenge_01.domain.enums.UserTypeEnum;
import com.postech.challenge_01.exceptions.UserIsNotOwnerException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserIsOwnerRuleTest {

    @Mock
    private IUserGateway userGateway;

    @Mock
    private IUserTypeGateway userTypeGateway;

    @InjectMocks
    private UserIsOwnerRule role;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        role = new UserIsOwnerRule(userGateway, userTypeGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldPassWhenUserIsOwner() {
        // Arrange
        Long ownerId = 1L;
        var restaurant = RestaurantBuilder.oneRestaurant().withOwnerId(ownerId).build();
        var user = UserBuilder.oneUser().build();
        var userTypeOwner = UserTypeBuilder.oneUserType()
                .withType(UserTypeEnum.Owner)
                .build();

        when(userGateway.findById(ownerId)).thenReturn(user);
        when(userTypeGateway.findById(ownerId)).thenReturn(userTypeOwner);

        // Act
        role.execute(restaurant);

        // Assert
        verify(userGateway, times(1)).findById(ownerId);
    }

    @Test
    void shouldNotPassWhenUserIsNotOwner() {
        // Arrange
        Long ownerId = 1L;
        var restaurant = RestaurantBuilder.oneRestaurant().withOwnerId(ownerId).build();
        var user = UserBuilder.oneUser().build();
        var userTypeOwner = UserTypeBuilder.oneUserType()
                .withType(UserTypeEnum.Client)
                .build();

        when(userGateway.findById(ownerId)).thenReturn(user);
        when(userTypeGateway.findById(ownerId)).thenReturn(userTypeOwner);

        // Act + Assert
        assertThrows(UserIsNotOwnerException.class, () -> role.execute(restaurant));
    }
}
