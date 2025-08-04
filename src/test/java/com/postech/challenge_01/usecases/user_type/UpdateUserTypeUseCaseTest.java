package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.application.usecases.user_type.UpdateUserTypeUseCase;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeUpdateRequestDTO;
import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import com.postech.challenge_01.application.mappers.UserTypeMapper;
import com.postech.challenge_01.builder.user_type.UserTypeBuilder;
import com.postech.challenge_01.builder.user_type.UserTypeUpdateRequestDTOBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateUserTypeUseCaseTest {
    @Mock
    private IUserTypeGateway gateway;

    @Mock
    private Rule<UserType> ruleMock;

    @InjectMocks
    private UpdateUserTypeUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        useCase = new UpdateUserTypeUseCase(gateway, List.of(ruleMock));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldUpdateUserTypeSuccessfully() {
        Long id = 1L;
        LocalDateTime lastModifiedDateTime = LocalDateTime.now();

        UserTypeUpdateRequestDTO requestDTO = UserTypeUpdateRequestDTOBuilder
                .oneUserTypeUpdateRequestDTO()
                .build();

        UserType updatedUserType = UserTypeBuilder
                .oneUserType()
                .withId(id)
                .withLastModifiedDateTime(lastModifiedDateTime)
                .build();

        UserType expectedResponse = UserTypeBuilder
                .oneUserType()
                .withId(id)
                .withLastModifiedDateTime(lastModifiedDateTime)
                .build();

        when(gateway.update(any(UserType.class), anyLong())).thenReturn(updatedUserType);

        UserType response = useCase.execute(requestDTO);

        verify(gateway, times(1)).update(any(UserType.class), eq(id));
        verify(ruleMock).execute(any(UserType.class));

        assertEquals(expectedResponse, response);
        assertThat(response.getId()).isEqualTo(id);
    }

    @Test
    void shouldThrowWhenRuleFails() {
        Long id = 1L;
        UserTypeUpdateRequestDTO requestDTO = UserTypeUpdateRequestDTOBuilder
                .oneUserTypeUpdateRequestDTO()
                .withId(id)
                .build();

        UserType userType = UserTypeBuilder
                .oneUserType()
                .withId(id)
                .build();

        try (MockedStatic<UserTypeMapper> mapper = mockStatic(UserTypeMapper.class)) {
            mapper.when(() -> UserTypeMapper.toUserType(id, requestDTO.data()))
                    .thenReturn(userType);

            doThrow(new RuntimeException("Falha na regra"))
                    .when(ruleMock).execute(userType);

            RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.execute(requestDTO));

            verify(ruleMock, times(1)).execute(userType);
            verify(gateway, never()).update(any(), anyLong());

            assertEquals("Falha na regra", ex.getMessage());
        }
    }

    @Test
    void shouldThrowWhenUserTypeNotFound() {
        Long id = 1L;
        UserTypeUpdateRequestDTO requestDTO = UserTypeUpdateRequestDTOBuilder
                .oneUserTypeUpdateRequestDTO()
                .withId(id)
                .build();

        UserType userType = UserTypeBuilder
                .oneUserType()
                .withId(id)
                .build();

        try (MockedStatic<UserTypeMapper> mapper = mockStatic(UserTypeMapper.class)) {
            mapper.when(() -> UserTypeMapper.toUserType(id, requestDTO.data()))
                    .thenReturn(userType);

            when(gateway.update(userType, id))
                    .thenThrow(new UserTypeNotFoundException(id));

            UserTypeNotFoundException ex = assertThrows(UserTypeNotFoundException.class, () -> useCase.execute(requestDTO));

            verify(ruleMock, times(1)).execute(userType);
            verify(gateway, times(1)).update(userType, id);

            assertTrue(ex.getMessage().contains(id.toString()));
        }
    }
}
