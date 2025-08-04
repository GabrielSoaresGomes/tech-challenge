package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.user.FindUserByIdUseCase;
import com.postech.challenge_01.builder.user.UserBuilder;
import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class FindUserByIdUseCaseTest {
    @Mock
    private IUserGateway gateway;

    @InjectMocks
    private FindUserByIdUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldReturnUserWhenFound() {
        Long id = 1L;
        LocalDateTime lastModifiedDateTime = LocalDateTime.now();
        User expectedResponse = UserBuilder
                .oneUser()
                .withId(id)
                .withLastModifiedDateTime(lastModifiedDateTime)
                .build();

        User user = UserBuilder
                .oneUser()
                .withId(id)
                .withLastModifiedDateTime(lastModifiedDateTime)
                .build();

        when(gateway.findById(anyLong())).thenReturn(user);

        User response = useCase.execute(id);

        verify(gateway, times(1)).findById(anyLong());
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(expectedResponse);
        assertThat(response).isInstanceOf(User.class);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        Long id = 1L;

        doThrow(ResourceNotFoundException.class).when(gateway).findById(anyLong());

        assertThrows(ResourceNotFoundException.class, () -> useCase.execute(id));

        verify(gateway).findById(id);
    }
}
