package com.postech.challenge_01.interface_adapter.data_sources.repositories.user;

import com.postech.challenge_01.builder.address.NewAddressDTOBuilder;
import com.postech.challenge_01.builder.user.NewUserDTOBuilder;
import com.postech.challenge_01.dtos.transfer.address.AddressDTO;
import com.postech.challenge_01.dtos.transfer.user.UserDTO;
import com.postech.challenge_01.dtos.transfer.user_address.NewUserAddressDTO;
import com.postech.challenge_01.infrastructure.data_sources.repositories.user_address.UserAddressRepository;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.AddressRepository;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.BaseIntegrationTest;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddressRepositoryIT extends BaseIntegrationTest {


    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;

    @Test
    void shoudFindAllByUserId() {
        //Arrange
        var savedUser = saveUser();
        var savedAddress1 = saveAddress("123");
        var savedAddress2 = saveAddress("1234");
        var savedAddress3 = saveAddress("12345");

        saveUserAddress(savedUser.id(), savedAddress1.id());
        saveUserAddress(savedUser.id(), savedAddress2.id());
        saveUserAddress(savedUser.id(), savedAddress3.id());

        //Act
        var resultList = addressRepository.findAllByUserId(savedUser.id(), 10, 0);

        //Assert
        assertEquals(3, resultList.size());
    }

    @Test
    @Transactional
    void shoudDeleteByUserId() {
        //Arrange
        var savedUser = saveUser();
        var savedAddress1 = saveAddress("1123");
        var savedAddress2 = saveAddress("11234");
        var savedAddress3 = saveAddress("112345");

        saveUserAddress(savedUser.id(), savedAddress1.id());
        saveUserAddress(savedUser.id(), savedAddress2.id());
        saveUserAddress(savedUser.id(), savedAddress3.id());

        //Act
        addressRepository.deleteByUserId(savedUser.id());

        //Assert
        var resultList = addressRepository.findAllByUserId(savedUser.id(), 10, 0);
        assertEquals(0, resultList.size());
    }

    private UserDTO saveUser() {
        var entity = NewUserDTOBuilder.oneNewUserDTO()
                .withLogin("teste.teste"+ LocalTime.now()).build();

        return userRepository.save(entity);
    }

    private AddressDTO saveAddress(String postalCode) {
        var entity = NewAddressDTOBuilder.oneNewAddressDTO()
                .withPostalCode(postalCode).build();

        return addressRepository.save(entity);
    }

    private void saveUserAddress(Long userId, Long addressId) {
        var newUserAddress = new NewUserAddressDTO(userId, addressId);
        userAddressRepository.save(newUserAddress);
    }
}
