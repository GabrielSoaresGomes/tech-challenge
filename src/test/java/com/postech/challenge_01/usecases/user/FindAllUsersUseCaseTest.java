package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.user.FindAllUsersUseCase;
import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class FindAllUsersUseCaseTest {
    @Mock
    private IUserGateway gateway;

    @InjectMocks
    private FindAllUsersUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        useCase = new FindAllUsersUseCase(gateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldReturnListOfUsers() {
        Pageable pageable = PageRequest.of(0, 10);

        User user1 = UserBuilder
                .oneUser()
                .withId(1L)
                .withName("Admin")
                .build();

        User user2 = UserBuilder
                .oneUser()
                .withId(2L)
                .withName("Client")
                .build();

        List<User> users = List.of(user1, user2);

        when(gateway.findAll(pageable)).thenReturn(users);

        List<User> result = useCase.execute(pageable);

        verify(gateway, times(1)).findAll(pageable);

        assertEquals(users.size(), result.size());
        assertEquals(user1.getId(), result.get(0).getId());
        assertEquals(user1.getName(), result.get(0).getName());
        assertEquals(user2.getId(), result.get(1).getId());
        assertEquals(user2.getName(), result.get(1).getName());
    }

    @Test
    void shouldThrowExceptionWhenGatewayFails() {
        Pageable pageable = PageRequest.of(0, 10);

        when(gateway.findAll(pageable)).thenThrow(new RuntimeException("Erro no gateway"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.execute(pageable));

        assertEquals("Erro no gateway", ex.getMessage());

        verify(gateway, times(1)).findAll(pageable);
    }
}
