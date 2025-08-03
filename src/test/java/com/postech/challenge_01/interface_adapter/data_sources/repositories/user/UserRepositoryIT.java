package com.postech.challenge_01.interface_adapter.data_sources.repositories.user;

import com.postech.challenge_01.builder.user.NewUserDTOBuilder;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserRepository;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryIT extends BaseIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shoudSaveUser() {
        //Arrange
        var entity = NewUserDTOBuilder.oneNewUserDTO()
                .withLogin("teste.teste1").build();

        //Act
        var savedUser = userRepository.save(entity);

        //Assert
        assertNotNull(savedUser.id());
    }

    @Test
    void shoudFindUserByIdSuccessfully() {
        //Arrange
        var entity = NewUserDTOBuilder.oneNewUserDTO()
                .withLogin("teste.teste2").build();

        var savedUser = userRepository.save(entity);

        //Act
        var userFound = userRepository.findById(savedUser.id());

        //Assert
        assertTrue(userFound.isPresent());
        assertEquals(userFound.get().id(), savedUser.id());
        assertEquals(userFound.get().name(), savedUser.name());
        assertEquals(userFound.get().login(), savedUser.login());
    }

    @Test
    void shoudNotFindUserById() {
        //Arrange
        var wrongId = 0L;

        //Act
        var userFound = userRepository.findById(wrongId);

        //Assert
        assertTrue(userFound.isEmpty());
    }

    @Test
    void shoudFindUserByLoginSuccessfully() {
        //Arrange
        var entity = NewUserDTOBuilder
                .oneNewUserDTO().build();

        var savedUser = userRepository.save(entity);

        //Act
        var userFound = userRepository.findByLogin(savedUser.login());

        //Assert
        assertTrue(userFound.isPresent());
        assertEquals(userFound.get().id(), savedUser.id());
        assertEquals(userFound.get().name(), savedUser.name());
        assertEquals(userFound.get().login(), savedUser.login());
    }

    @Test
    void shoudNotFindUserByLogin() {
        //Arrange
        var wrongLogin = "wronglogin.teste";

        //Act
        var userFound = userRepository.findByLogin(wrongLogin);

        //Assert
        assertTrue(userFound.isEmpty());
    }

    @Test
    void shoudUpdatePassword() {
        // Arrange
        var oldPassword = "old1234";
        var newPassword = "new1234";

        //Arrange
        var entity = NewUserDTOBuilder.oneNewUserDTO()
                .withLogin("teste.teste3")
                .withPassword(oldPassword).build();

        var savedUser = userRepository.save(entity);

        // Act
        userRepository.updatePassword(savedUser.id(), newPassword);

        // Verify
        var opUpdatedUser = userRepository.findById(savedUser.id());

        assertTrue(opUpdatedUser.isPresent());
        var updatedUser = opUpdatedUser.get();

        assertEquals(updatedUser.password(), newPassword);
    }
}
