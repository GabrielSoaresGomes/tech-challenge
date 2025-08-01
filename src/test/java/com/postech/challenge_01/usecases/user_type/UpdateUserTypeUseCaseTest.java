package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.domains.UserType;
import com.postech.challenge_01.domains.enums.UserTypeEnum;
import com.postech.challenge_01.dtos.responses.UserTypeResponseDTO;
import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import com.postech.challenge_01.mappers.UserTypeMapper;
import com.postech.challenge_01.repositories.UserTypeRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import com.postech.challenge_01.builder.UserTypeBuilder;
import com.postech.challenge_01.builder.UserTypeUpdateRequestDTOBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class UpdateUserTypeUseCaseTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @Mock
    private Rule<UserType> ruleMock;

    @InjectMocks
    private UpdateUserTypeUseCase updateUserTypeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        updateUserTypeUseCase = new UpdateUserTypeUseCase(userTypeRepository, List.of(ruleMock));
    }

    @Test
    void shouldUpdateUserTypeSuccessfully() {
        Long id = 1L;
        String newName = "Admin";
        UserTypeEnum newType = UserTypeEnum.Client;

        var request = UserTypeUpdateRequestDTOBuilder
                .oneUserTypeUpdateRequestDTO()
                .withId(id)
                .withName(newName)
                .build();

        UserType entity = UserTypeBuilder.oneUserType().withId(id).withName(newName).build();

        UserType updatedEntity = UserTypeBuilder.oneUserType().withId(id).withName(newName).build();

        try (MockedStatic<UserTypeMapper> mockedMapper = mockStatic(UserTypeMapper.class)) {
            mockedMapper.when(() -> UserTypeMapper.userTypeRequestDTOToUserType(id, request.data()))
                    .thenReturn(entity);

            mockedMapper.when(() -> UserTypeMapper.userTypeToUserTypeResponseDTO(updatedEntity))
                    .thenReturn(new UserTypeResponseDTO(id, newName, newType));

            when(userTypeRepository.update(entity, id)).thenReturn(Optional.of(updatedEntity));

            UserTypeResponseDTO response = updateUserTypeUseCase.execute(request);

            verify(ruleMock).execute(entity);
            verify(userTypeRepository, times(1)).update(entity, id);

            assertThat(response).isNotNull();
            assertThat(response.id()).isEqualTo(id);
            assertThat(response.name()).isEqualTo(newName);
        }
    }

    @Test
    void shouldThrowExceptionWhenRuleFails() {
        Long id = 1L;
        String newName = "Admin";

        var request = UserTypeUpdateRequestDTOBuilder
                .oneUserTypeUpdateRequestDTO()
                .withId(id)
                .withName(newName)
                .build();

        UserType entity = UserTypeBuilder.oneUserType().withId(id).withName(newName).build();

        try (MockedStatic<UserTypeMapper> mockedMapper = mockStatic(UserTypeMapper.class)) {
            mockedMapper.when(() -> UserTypeMapper.userTypeRequestDTOToUserType(id, request.data()))
                    .thenReturn(entity);

            doThrow(new RuntimeException("Falha na regra")).when(ruleMock).execute(entity);

            assertThatThrownBy(() -> updateUserTypeUseCase.execute(request))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Falha na regra");

            verify(ruleMock).execute(entity);
            verify(userTypeRepository, never()).update(any(), anyLong());
        }
    }

    @Test
    void shouldThrowUserTypeNotFoundExceptionWhenUpdateFails() {
        Long id = 999L;
        String newName = "NonExistent";

        var request = UserTypeUpdateRequestDTOBuilder
                .oneUserTypeUpdateRequestDTO()
                .withId(id)
                .withName(newName)
                .build();

        UserType entity = UserTypeBuilder.oneUserType().withId(id).withName(newName).build();

        try (MockedStatic<UserTypeMapper> mockedMapper = mockStatic(UserTypeMapper.class)) {
            mockedMapper.when(() -> UserTypeMapper.userTypeRequestDTOToUserType(id, request.data()))
                    .thenReturn(entity);

            when(userTypeRepository.update(entity, id)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> updateUserTypeUseCase.execute(request))
                    .isInstanceOf(UserTypeNotFoundException.class)
                    .hasMessageContaining(id.toString());

            verify(ruleMock).execute(entity);
            verify(userTypeRepository, times(1)).update(entity, id);
        }
    }
}
