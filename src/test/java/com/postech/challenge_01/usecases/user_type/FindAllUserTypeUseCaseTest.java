package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.builder.UserTypeBuilder;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.domain.enums.UserTypeEnum;
import com.postech.challenge_01.dtos.responses.UserTypeResponseDTO;
import com.postech.challenge_01.application.usecases.user_type.FindAllUserTypeUseCase;
import com.postech.challenge_01.infrastructure.data_sources.repositories.user_type.UserTypeRepository;
import com.postech.challenge_01.mappers.UserTypeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class FindAllUserTypeUseCaseTest {
    @Mock
    private UserTypeRepository userTypeRepository;

    @InjectMocks
    private FindAllUserTypeUseCase findAllUserTypeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        findAllUserTypeUseCase =  new FindAllUserTypeUseCase(userTypeRepository);
    }

    @Test
    void shouldReturnListOfUserTypeResponseDTO() {
        Pageable pageable = PageRequest.of(0, 2);

        List<UserType> userTypeList = List.of(
                UserTypeBuilder.oneUserType().withId(1L).withName("Admin").build(),
                UserTypeBuilder.oneUserType().withId(2L).withName("Client").build()
        );

        List<UserTypeResponseDTO> expectedDTOList = List.of(
                new UserTypeResponseDTO(1L, "Admin", UserTypeEnum.Owner),
                new UserTypeResponseDTO(2L, "Client", UserTypeEnum.Client)
        );

        when(userTypeRepository.findAll(pageable.getPageSize(), pageable.getOffset()))
                .thenReturn(userTypeList);

        try (MockedStatic<UserTypeMapper> mockedMapper = Mockito.mockStatic(UserTypeMapper.class)) {
            mockedMapper.when(() -> UserTypeMapper.userTypeToUserTypeResponseDTOList(userTypeList))
                    .thenReturn(expectedDTOList);

            List<UserTypeResponseDTO> result = findAllUserTypeUseCase.execute(pageable);

            verify(userTypeRepository, times(1))
                    .findAll(pageable.getPageSize(), pageable.getOffset());

            assertThat(result).isNotNull();
            assertThat(result).hasSize(2);
            assertThat(result.get(0).id()).isEqualTo(1L);
            assertThat(result.get(1).name()).isEqualTo("Client");
        }
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {
        Pageable pageable = PageRequest.of(0, 2);

        when(userTypeRepository.findAll(pageable.getPageSize(), pageable.getOffset()))
                .thenThrow(new RuntimeException("Falha no banco"));

        assertThatThrownBy(() -> findAllUserTypeUseCase.execute(pageable))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Falha no banco");

        verify(userTypeRepository, times(1))
                .findAll(pageable.getPageSize(), pageable.getOffset());
    }
}
