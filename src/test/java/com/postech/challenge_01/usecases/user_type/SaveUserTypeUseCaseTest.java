package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.domains.UserType;
import com.postech.challenge_01.domains.enums.UserTypeEnum;
import com.postech.challenge_01.dtos.requests.UserTypeRequestDTO;
import com.postech.challenge_01.dtos.responses.UserTypeResponseDTO;
import com.postech.challenge_01.mappers.UserTypeMapper;
import com.postech.challenge_01.repositories.UserTypeRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import com.postech.challenge_01.builder.UserTypeBuilder;
import com.postech.challenge_01.builder.UserTypeRequestDTOBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class SaveUserTypeUseCaseTest {
    @Mock
    private UserTypeRepository userTypeRepository;

    @Mock
    private Rule<UserType> ruleMock;

    @InjectMocks
    private SaveUserTypeUseCase saveUserTypeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saveUserTypeUseCase =  new SaveUserTypeUseCase(userTypeRepository, List.of(ruleMock));
    }

    @Test
    void shouldExecuteAndSaveUserTypeSuccessfully() {
        var id = 1L;
        var name = "Admin";

        var request = UserTypeRequestDTOBuilder
                .oneUserTypeRequestDTO()
                .withName(name)
                .build();

        var savedUserType = UserTypeBuilder
                .oneUserType()
                .withId(id)
                .withName(name)
                .build();

        when(userTypeRepository.save(any(UserType.class))).thenReturn(savedUserType);

        UserTypeResponseDTO response = saveUserTypeUseCase.execute(request);

        verify(ruleMock).execute(any(UserType.class));
        verify(userTypeRepository, times(1)).save(any(UserType.class));

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.name()).isEqualTo(name);
    }

    @Test
    void shouldThrowExceptionWhenRuleFails() {
        var request = UserTypeRequestDTOBuilder
                .oneUserTypeRequestDTO()
                .withName("Admin")
                .build();

        doThrow(new RuntimeException("Falha na regra")).when(ruleMock).execute(any(UserType.class));

        assertThatThrownBy(() -> saveUserTypeUseCase.execute(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Falha na regra");

        verify(ruleMock).execute(any(UserType.class));
        verify(userTypeRepository, never()).save(any(UserType.class));
    }

    @Test
    void shouldThrowExceptionWhenSavingUserTypeFails() {
        UserTypeRequestDTO requestDTO = new UserTypeRequestDTO("Admin", UserTypeEnum.Owner);

        UserType entity = UserTypeBuilder
                .oneUserType()
                .withId(null)
                .withName("Admin")
                .build();

        when(userTypeRepository.save(entity))
                .thenThrow(new RuntimeException("Falha ao salvar o tipo de usuário"));

        try (MockedStatic<UserTypeMapper> mockedMapper = mockStatic(UserTypeMapper.class)) {
            mockedMapper.when(() -> UserTypeMapper.userTypeRequestDTOToUserType(requestDTO))
                    .thenReturn(entity);

            assertThatThrownBy(() -> saveUserTypeUseCase.execute(requestDTO))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Falha ao salvar o tipo de usuário");

            verify(userTypeRepository, times(1)).save(entity);
        }
    }
}
