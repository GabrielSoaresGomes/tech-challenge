package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import com.postech.challenge_01.repositories.user_type.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class DeleteUserTypeUseCaseTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @InjectMocks
    private DeleteUserTypeUseCase deleteUserTypeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteUserTypeSuccessfully() {
        Long id = 1L;

        when(userTypeRepository.delete(id)).thenReturn(1);

        deleteUserTypeUseCase.execute(id);

        verify(userTypeRepository, times(1)).delete(id);
    }

    @Test
    void shouldThrowExceptionWhenUserTypeNotFound() {
        Long id = 999L;

        when(userTypeRepository.delete(id)).thenReturn(0);

        assertThatThrownBy(() -> deleteUserTypeUseCase.execute(id))
                .isInstanceOf(UserTypeNotFoundException.class)
                .hasMessageContaining(id.toString());

        verify(userTypeRepository, times(1)).delete(id);
    }
}
