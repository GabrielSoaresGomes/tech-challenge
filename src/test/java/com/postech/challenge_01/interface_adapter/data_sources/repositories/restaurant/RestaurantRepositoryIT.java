package com.postech.challenge_01.interface_adapter.data_sources.repositories.restaurant;

import com.postech.challenge_01.builder.address.NewAddressDTOBuilder;
import com.postech.challenge_01.builder.user.NewUserDTOBuilder;
import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;
import com.postech.challenge_01.dtos.transfer.address.AddressDTO;
import com.postech.challenge_01.dtos.transfer.restaurant.NewRestaurantDTO;
import com.postech.challenge_01.dtos.transfer.user.UserDTO;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.AddressRepository;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.RestaurantRepository;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
public class RestaurantRepositoryIT {

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("tech-challenge-01")
            .withUsername("root")
            .withPassword("root");

    @DynamicPropertySource
    public static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    private UserDTO userDTO;
    private AddressDTO addressDTO;

    @Test
    void shoudFindOpenedRestaurants() {
        //Arrange
        saveOpenedRestaurant("Restaurant 1");
        saveOpenedRestaurant("Restaurant 2");
        saveOpenedRestaurant("Restaurant 3");
        saveClosedRestaurant("Restaurant 4");

        //Act
        var resultList = restaurantRepository.findAllOpen(10, 0);

        //Assert
        assertEquals(3, resultList.size());
    }

    private void saveOpenedRestaurant(String name) {
        saveRestaurant(name, LocalTime.MIN);
    }

    private void saveClosedRestaurant(String name) {
        saveRestaurant(name, LocalTime.now().plusHours(1));
    }

    private void saveRestaurant(String name, LocalTime startTime) {
        saveUser();
        saveAddress();

        var restaurandDTO = new NewRestaurantDTO(
                userDTO.id(),
                name,
                RestaurantGenreEnum.BRAZILIAN,
                startTime,
                LocalTime.MAX.minusSeconds(1),
                addressDTO.id()
        );

        restaurantRepository.save(restaurandDTO);
    }

    private void saveUser() {
        if(userDTO != null) {
            return;
        }

        var entity = NewUserDTOBuilder.oneNewUserDTO()
                .withLogin("teste.teste"+ LocalTime.now()).build();

        userDTO = userRepository.save(entity);
    }

    private void saveAddress() {
        if(addressDTO != null) {
            return;
        }

        var entity = NewAddressDTOBuilder.oneNewAddressDTO().build();

        addressDTO = addressRepository.save(entity);
    }
}
