package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.domains.UserType;
import com.postech.challenge_01.dtos.responses.UserTypeResponseDTO;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.mappers.UserTypeMapper;
import com.postech.challenge_01.repositories.UserTypeRepository;
import com.postech.challenge_01.builder.UserTypeBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class FindUserTypeByIdUseCaseTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @InjectMocks
    private FindUserTypeByIdUseCase findUserTypeByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnUserTypeWhenFound() {
        Long id = 1L;
        UserType entity = UserTypeBuilder.oneUserType().withId(id).withName("Admin").build();
        UserTypeResponseDTO response = new UserTypeResponseDTO(id, "Admin", "Junior3");

        when(userTypeRepository.findById(id)).thenReturn(Optional.of(entity));

        try (MockedStatic<UserTypeMapper> mockedMapper = mockStatic(UserTypeMapper.class)) {
            mockedMapper.when(() -> UserTypeMapper.userTypeToUserTypeResponseDTO(entity)).thenReturn(response);

            UserTypeResponseDTO result = findUserTypeByIdUseCase.execute(id);

            assertThat(result).isNotNull();
            assertThat(result.id()).isEqualTo(id);
            assertThat(result.name()).isEqualTo("Admin");

            verify(userTypeRepository, times(1)).findById(id);
        }
    }

    @Test
    void shouldThrowExceptionWhenUserTypeNotFound() {
        Long id = 999L;

        when(userTypeRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findUserTypeByIdUseCase.execute(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Tipo de usuário não encontrado");

        verify(userTypeRepository, times(1)).findById(id);
    }
}
