package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.application.gateways.IPasswordEncoderGateway;
import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.mappers.UserMapper;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.application.usecases.user.UpdateUserUseCase;
import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.builder.user.UserUpdateRequestDTOBuilder;
import com.postech.challenge_01.domain.User;
import org.junit.jupiter.api.*;

import org.mockito.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateUserUseCaseTest {

    @Mock
    private IUserGateway gateway;

    @Mock
    private IPasswordEncoderGateway passwordEncoderGateway;

    @Mock
    private Rule<User> ruleMock;

    @InjectMocks
    private UpdateUserUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        useCase = new UpdateUserUseCase(gateway, passwordEncoderGateway, List.of(ruleMock));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldUpdateUserSuccessfully() {
        Long id = 1L;

        var userUpdateRequestDTO = UserUpdateRequestDTOBuilder
                .oneUserUpdateRequestDTO()
                .withId(id)
                .build();

        var encodedPassword = "encodedPassword123";

        var updatedUser = UserBuilder
                .oneUser()
                .withId(id)
                .build();

        var expectedUser = UserBuilder
                .oneUser()
                .withId(id)
                .build();

        when(passwordEncoderGateway.encode(anyString())).thenReturn(encodedPassword);

        try (MockedStatic<UserMapper> mapper = mockStatic(UserMapper.class)) {
            mapper.when(() -> UserMapper.toUser(eq(id), any(), eq(encodedPassword)))
                    .thenReturn(updatedUser);

            when(gateway.update(updatedUser, id)).thenReturn(expectedUser);

            User response = useCase.execute(userUpdateRequestDTO);

            verify(passwordEncoderGateway).encode(anyString());
            verify(ruleMock).execute(updatedUser);
            verify(gateway).update(updatedUser, id);

            assertEquals(expectedUser, response);
            assertThat(response.getId()).isEqualTo(id);
        }
    }

    @Test
    void shouldThrowWhenRuleFails() {
        Long id = 1L;

        var userUpdateRequestDTO = UserUpdateRequestDTOBuilder
                .oneUserUpdateRequestDTO()
                .withId(id)
                .build();

        var encodedPassword = "encodedPassword123";

        var user = UserBuilder
                .oneUser()
                .withId(id)
                .build();

        when(passwordEncoderGateway.encode(anyString())).thenReturn(encodedPassword);

        try (MockedStatic<UserMapper> mapper = mockStatic(UserMapper.class)) {
            mapper.when(() -> UserMapper.toUser(eq(id), any(), eq(encodedPassword)))
                    .thenReturn(user);

            doThrow(new RuntimeException("Falha na regra")).when(ruleMock).execute(user);

            RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.execute(userUpdateRequestDTO));

            verify(ruleMock).execute(user);
            verify(gateway, never()).update(any(), anyLong());
            assertEquals("Falha na regra", ex.getMessage());
        }
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        Long id = 1L;

        var userUpdateRequestDTO = UserUpdateRequestDTOBuilder
                .oneUserUpdateRequestDTO()
                .withId(id)
                .build();

        var encodedPassword = "encodedPassword123";

        var user = UserBuilder
                .oneUser()
                .withId(id)
                .build();

        when(passwordEncoderGateway.encode(anyString())).thenReturn(encodedPassword);

        RuntimeException notFoundException = new RuntimeException("Usuário não encontrado");

        try (MockedStatic<UserMapper> mapper = mockStatic(UserMapper.class)) {
            mapper.when(() -> UserMapper.toUser(eq(id), any(), eq(encodedPassword)))
                    .thenReturn(user);

            when(gateway.update(user, id)).thenThrow(notFoundException);

            RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.execute(userUpdateRequestDTO));

            verify(ruleMock).execute(user);
            verify(gateway).update(user, id);
            assertEquals("Usuário não encontrado", ex.getMessage());
        }
    }
}
