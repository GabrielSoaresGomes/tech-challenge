package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.builder.UserBuider;
import com.postech.challenge_01.builder.UserRequestDTOBuider;
import com.postech.challenge_01.domains.User;
import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.repositories.user.UserRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaveUserUseCaseTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Rule<User> ruleMock;

    @InjectMocks
    private SaveUserUseCase saveUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saveUserUseCase = new SaveUserUseCase(userRepository, passwordEncoder, List.of(ruleMock));
    }

    @Test
    void shouldExecuteAndSaveUserSuccessfully() {
        // Arrange
        var id = 1L;
        var name = "Nome Teste";
        var email = "teste@teste.com";
        var login = "teste.teste";
        var encodedPassword = "encodedPassword123";

        UserRequestDTO request = UserRequestDTOBuider
                .oneUserRequestDTO()
                .withName(name)
                .withEmail(email)
                .withLogin(login)
                .build();

        User savedUser = UserBuider
                .oneUser()
                .withId(id)
                .build();

        when(passwordEncoder.encode(request.password())).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        //Act
        UserResponseDTO response = saveUserUseCase.execute(request);

        //Assert
        verify(passwordEncoder, times(1)).encode(request.password());
        verify(ruleMock).execute(any(User.class));
        verify(userRepository, times(1)).save(any(User.class));

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.name()).isEqualTo(name);
        assertThat(response.email()).isEqualTo(email);
        assertThat(response.login()).isEqualTo(login);
    }

    @Test
    void shouldThrowInvalidRule() {
        //Arrange
        var encodedPassword = "encodedPassword123";

        UserRequestDTO request = UserRequestDTOBuider.oneUserRequestDTO().build();

        when(passwordEncoder.encode(request.password())).thenReturn(encodedPassword);
        doThrow(new RuntimeException("any rules")).when(ruleMock).execute(any(User.class));

        //Assert
        assertThrows(RuntimeException.class, () -> saveUserUseCase.execute(request));
        verify(userRepository, never()).save(any(User.class));
    }

}
