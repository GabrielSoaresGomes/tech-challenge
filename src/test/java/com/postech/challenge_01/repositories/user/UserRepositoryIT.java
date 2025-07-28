package com.postech.challenge_01.repositories.user;

import com.postech.challenge_01.builder.UserBuilder;
import com.postech.challenge_01.repositories.BaseIntegrationTest;
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
        var entity = UserBuilder.oneUser().build();

        //Act
        var savedUser = userRepository.save(entity);

        //Assert
        assertNotNull(savedUser.getId());
    }

    @Test
    void shoudFindUserByIdSuccessfully() {
        //Arrange
        var entity = UserBuilder.oneUser().build();
        var savedUser = userRepository.save(entity);

        //Act
        var userFound = userRepository.findById(savedUser.getId());

        //Assert
        assertTrue(userFound.isPresent());
        assertEquals(userFound.get().getId(), savedUser.getId());
        assertEquals(userFound.get().getName(), savedUser.getName());
        assertEquals(userFound.get().getLogin(), savedUser.getLogin());
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
        var entity = UserBuilder
                .oneUser()
                .build();

        var savedUser = userRepository.save(entity);

        //Act
        var userFound = userRepository.findByLogin(savedUser.getLogin());

        //Assert
        assertTrue(userFound.isPresent());
        assertEquals(userFound.get().getId(), savedUser.getId());
        assertEquals(userFound.get().getName(), savedUser.getName());
        assertEquals(userFound.get().getLogin(), savedUser.getLogin());
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
}
