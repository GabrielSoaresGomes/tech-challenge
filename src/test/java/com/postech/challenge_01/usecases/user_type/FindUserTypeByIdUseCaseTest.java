package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.user_type.FindUserTypeByIdUseCase;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.builder.user_type.UserTypeBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindUserTypeByIdUseCaseTest {
    @Mock
    private IUserTypeGateway gateway;

    @InjectMocks
    private FindUserTypeByIdUseCase useCase;

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
    void shouldReturnUserTypeWhenFound() {
        Long id = 1L;
        LocalDateTime lastModifiedDateTime = LocalDateTime.now();
        UserType expectedResponse = UserTypeBuilder
                .oneUserType()
                .withId(id)
                .withLastModifiedDateTime(lastModifiedDateTime)
                .build();

        UserType userType = UserTypeBuilder
                .oneUserType().
                withId(id)
                .withLastModifiedDateTime(lastModifiedDateTime)
                .build();

        when(gateway.findById(anyLong())).thenReturn(userType);

        UserType response = useCase.execute(id);

        verify(gateway, times(1)).findById(anyLong());
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(expectedResponse);
        assertThat(response).isInstanceOf(UserType.class);
    }

    @Test
    void shouldThrowExceptionWhenUserTypeNotFound() {
        Long id = 1L;

        doThrow(ResourceNotFoundException.class).when(gateway).findById(anyLong());

        assertThrows(ResourceNotFoundException.class, () -> useCase.execute(id));

        verify(gateway).findById(id);
    }
}
