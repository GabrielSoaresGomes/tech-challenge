package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.builder.user.UserDTOBuilder;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserGatewayTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserGateway gateway;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        gateway = new UserGateway(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldFindById() {
        // Arrange
        var id = 1L;
        var userDTO = UserDTOBuilder.oneUserDTO()
                .withId(id).build();

        when(repository.findById(id)).thenReturn(Optional.ofNullable(userDTO));

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
        var userDTO1 = UserDTOBuilder.oneUserDTO().build();
        var userDTO2 = UserDTOBuilder.oneUserDTO().build();
        var userDTO3 = UserDTOBuilder.oneUserDTO().build();
        var userDTOList = List.of(userDTO1, userDTO2, userDTO3);

        when(repository.findAll(anyInt(), anyLong())).thenReturn(userDTOList);

        // Act
        var result = gateway.findAll(PageRequest.of(0, 10));

        // Assert
        assertEquals(3, result.size());
    }

    @Test
    void shouldSave() {
        // Arrange
        var user = UserBuilder.oneUser().build();
        var userDTO = UserDTOBuilder.oneUserDTO().build();

        when(repository.save(any())).thenReturn(userDTO);

        // Act
        var result = gateway.save(user);

        // Assert
        assertNotNull(result.getId());
        assertEquals(result.getLogin(), userDTO.login());
    }

    @Test
    void shouldUpdateWhenUserExist() {
        // Arrange
        var id = 1L;
        var user = UserBuilder.oneUser().build();
        var userDTO = UserDTOBuilder.oneUserDTO().build();

        when(repository.update(any())).thenReturn(Optional.ofNullable(userDTO));

        // Act
        var result = gateway.update(user, id);

        // Assert
        assertEquals(result.getName(), userDTO.name());
        assertEquals(result.getLogin(), userDTO.login());
    }

    @Test
    void shouldThrowUpdateWhenUserNotExist() {
        // Arrange
        var id = 1L;
        var user = UserBuilder.oneUser().build();
        when(repository.update(any())).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(UserNotFoundException.class, () -> gateway.update(user, id));
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
    void shouldThrowDeleteWhenUserNotExist() {
        // Arrange
        var id = 1L;
        when(repository.delete(id)).thenReturn(0);

        // Act + Assert
        assertThrows(UserNotFoundException.class, () -> gateway.delete(id));
    }

    @Test
    void shuldFindByLogin() {
        // Arrange
        var login = "teste@domain.com";
        var userDTO = UserDTOBuilder.oneUserDTO()
                .withLogin(login).build();

        when(repository.findByLogin(login)).thenReturn(Optional.ofNullable(userDTO));

        // Act
        var result = gateway.findByLogin(login);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(result.get().getLogin(), login);
    }

    @Test
    void shuldRequireByLogin() {
        // Arrange
        var login = "teste@domain.com";
        var userDTO = UserDTOBuilder.oneUserDTO()
                .withLogin(login).build();

        when(repository.findByLogin(login)).thenReturn(Optional.ofNullable(userDTO));

        // Act
        var result = gateway.requireByLogin(login);

        // Assert
        assertEquals(result.getLogin(), login);
    }

    @Test
    void shuldThrowWhenRequireByLoginDoNotReturn() {
        // Arrange
        var login = "teste@domain.com";

        when(repository.findByLogin(login)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(UsernameNotFoundException.class, ()-> gateway.requireByLogin(login));
    }

    @Test
    void shuldUpdatePassword() {
        // Arrange
        var id = 1L;
        var password = "12345";
        when(repository.updatePassword(any(), any())).thenReturn(true);

        // Act + Assert
        assertDoesNotThrow(()-> gateway.updatePassword(id, password));
    }

    @Test
    void shuldThrowWhenUpdatePasswordDoesNotUpdate() {
        // Arrange
        var id = 1L;
        var password = "12345";

        when(repository.updatePassword(any(), any())).thenReturn(false);

        // Act + Assert
        assertThrows(UserNotFoundException.class, ()-> gateway.updatePassword(id, password));
    }

    // find by user type id
    @Test
    void shouldFindByUserTypeId() {
        // Arrange
        var userTypeId = 1L;
        var userDTO = UserDTOBuilder.oneUserDTO()
                .withUserTypeId(userTypeId).build();

        when(repository.findByUserTypeId(userTypeId)).thenReturn(List.of(userDTO));

        // Act
        var response = gateway.findByUserTypeId(userTypeId);

        assertFalse(response.isEmpty());
        assertEquals(response.get(0).getUserTypeId(), userTypeId);
    }

}
