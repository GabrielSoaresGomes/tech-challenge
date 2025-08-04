package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.application.usecases.user_type.SaveUserTypeUseCase;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeRequestDTO;
import com.postech.challenge_01.builder.user_type.UserTypeBuilder;
import com.postech.challenge_01.builder.user_type.UserTypeRequestDTOBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaveUserTypeUseCaseTest {
    @Mock
    private IUserTypeGateway gateway;

    @Mock
    private Rule<UserType> ruleMock;

    @InjectMocks
    private SaveUserTypeUseCase useCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        useCase = new SaveUserTypeUseCase(gateway, List.of(ruleMock));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void shouldSaveUserTypeSuccessfully() {
        Long id = 1L;

        UserTypeRequestDTO requestDTO = UserTypeRequestDTOBuilder
                .oneUserTypeRequestDTO()
                .build();

        UserType savedUserType = UserTypeBuilder
                .oneUserType()
                .withId(id)
                .withName("Nome")
                .build();

        when(gateway.save(any(UserType.class))).thenReturn(savedUserType);

        UserType response = useCase.execute(requestDTO);

        verify(ruleMock).execute(any(UserType.class));
        verify(gateway).save(any(UserType.class));
        assertNotNull(response);
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(requestDTO.name());
        }

    @Test
    void shouldFailWhenRuleThrows() {
        UserTypeRequestDTO requestDTO = UserTypeRequestDTOBuilder
                .oneUserTypeRequestDTO()
                .build();

        doThrow(RuntimeException.class).when(ruleMock).execute(any(UserType.class));

        assertThrows(RuntimeException.class, () -> useCase.execute(requestDTO));
        verify(ruleMock).execute(any(UserType.class));
        verify(gateway, never()).save(any(UserType.class));
    }
}

