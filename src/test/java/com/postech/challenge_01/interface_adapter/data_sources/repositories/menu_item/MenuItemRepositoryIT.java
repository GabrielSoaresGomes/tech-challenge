package com.postech.challenge_01.interface_adapter.data_sources.repositories.menu_item;

import com.postech.challenge_01.builder.address.NewAddressDTOBuilder;
import com.postech.challenge_01.builder.menu_item.NewMenuItemDTOBuilder;
import com.postech.challenge_01.builder.user.NewUserDTOBuilder;
import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;
import com.postech.challenge_01.dtos.transfer.address.AddressDTO;
import com.postech.challenge_01.dtos.transfer.menu.MenuDTO;
import com.postech.challenge_01.dtos.transfer.menu.NewMenuDTO;
import com.postech.challenge_01.dtos.transfer.restaurant.NewRestaurantDTO;
import com.postech.challenge_01.dtos.transfer.restaurant.RestaurantDTO;
import com.postech.challenge_01.dtos.transfer.user.UserDTO;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MenuItemRepositoryIT extends BaseIntegrationTest {

    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    private MenuDTO menuDTO;
    private RestaurantDTO restaurantDTO;
    private UserDTO userDTO;
    private AddressDTO addressDTO;

    @Test
    void shoudFindAllByMenuId() {
        //Arrange
        saveMenuItem("Item 1");
        saveMenuItem("Item 2");
        saveMenuItem("Item 3");

        //Act
        var resultList = menuItemRepository.findAllByMenuId(menuDTO.id(), 10, 0);

        //Assert
        assertEquals(3, resultList.size());
    }

    @Test
    void shoudNotFindAllByMenuId() {
        //Act
        var resultList = menuItemRepository.findAllByMenuId(33L, 10, 0);

        //Assert
        assertEquals(0, resultList.size());
    }

    private void saveMenuItem(String menuItemName) {
        saveMenu();
        var menuItemDTO = NewMenuItemDTOBuilder.oneMenuItem()
                .withMenuId(menuDTO.id())
                .withName(menuItemName)
                .build();

        menuItemRepository.save(menuItemDTO);
    }

    private void saveMenu() {
        if(menuDTO != null) {
            return;
        }

        saveRestaurant();

        var newMenuDTO = new NewMenuDTO(
                restaurantDTO.id()
        );

        menuDTO = menuRepository.save(newMenuDTO);
    }

    private void saveRestaurant() {
        if(restaurantDTO != null) {
            return;
        }

        saveUser();
        saveAddress();

        var restaurandDTO = new NewRestaurantDTO(
                userDTO.id(),
                "teste",
                RestaurantGenreEnum.BRAZILIAN,
                LocalTime.now(),
                LocalTime.now().plusHours(8),
                addressDTO.id()
        );

        restaurantDTO = restaurantRepository.save(restaurandDTO);
    }

    private void saveUser() {
        if(userDTO != null) {
            return;
        }

        var entity = NewUserDTOBuilder.oneNewUserDTO()
                .withLogin("teste.teste4").build();

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
