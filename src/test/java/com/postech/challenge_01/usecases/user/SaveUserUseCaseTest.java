package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.application.usecases.user.SaveUserUseCase;
import com.postech.challenge_01.builder.UserBuilder;
import com.postech.challenge_01.builder.UserRequestDTOBuilder;
import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.dtos.requests.user.UserRequestDTO;
import com.postech.challenge_01.interface_adapter.gateways.PasswordEncoderGateway;
import com.postech.challenge_01.interface_adapter.gateways.UserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SaveUserUseCaseTest {
    @Mock
    private UserGateway userGateway;

    @Mock
    private PasswordEncoderGateway passwordEncoderGateway;

    @Mock
    private Rule<User> ruleMock;

    @InjectMocks
    private SaveUserUseCase saveUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saveUserUseCase = new SaveUserUseCase(userGateway, passwordEncoderGateway, List.of(ruleMock));
    }

    @Test
    void shouldExecuteAndSaveUserSuccessfully() {
        // Arrange
        var id = 1L;
        var userTypeId = 1L;
        var name = "Nome Teste";
        var email = "teste@teste.com";
        var login = "teste.teste";
        var encodedPassword = "encodedPassword123";

        UserRequestDTO request = UserRequestDTOBuilder
                .oneUserRequestDTO()
                .withUserTypeId(userTypeId)
                .withName(name)
                .withEmail(email)
                .withLogin(login)
                .build();

        User savedUser = UserBuilder
                .oneUser()
                .withId(id)
                .build();

        when(passwordEncoderGateway.encode(request.password())).thenReturn(encodedPassword);
        when(userGateway.save(any(User.class))).thenReturn(savedUser);

        //Act
        User response = saveUserUseCase.execute(request);

        //Assert
        verify(passwordEncoderGateway, times(1)).encode(request.password());
        verify(ruleMock).execute(any(User.class));
        verify(userGateway, times(1)).save(any(User.class));

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getEmail()).isEqualTo(email);
        assertThat(response.getLogin()).isEqualTo(login);
    }

    @Test
    void shouldThrowInvalidRule() {
        //Arrange
        var encodedPassword = "encodedPassword123";

        UserRequestDTO request = UserRequestDTOBuilder.oneUserRequestDTO().build();

        when(passwordEncoderGateway.encode(request.password())).thenReturn(encodedPassword);
        doThrow(new RuntimeException("any rules")).when(ruleMock).execute(any(User.class));

        //Assert
        assertThrows(RuntimeException.class, () -> saveUserUseCase.execute(request));
        verify(userGateway, never()).save(any(User.class));
    }

}
