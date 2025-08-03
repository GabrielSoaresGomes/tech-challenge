package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.user_type.*;
import com.postech.challenge_01.builder.user_type.UserTypeBuilder;
import com.postech.challenge_01.builder.user_type.UserTypeRequestDTOBuilder;
import com.postech.challenge_01.builder.user_type.UserTypeUpdateDataDTOBuilder;
import com.postech.challenge_01.builder.user_type.UserTypeUpdateRequestDTOBuilder;
import com.postech.challenge_01.interface_adapter.presenters.UserTypePresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserTypeControllerTest {

    @Mock
    private FindAllUserTypeUseCase findAllUserTypesUseCase;
    @Mock
    private FindUserTypeByIdUseCase findUserTypeByIdUseCase;
    @Mock
    private SaveUserTypeUseCase saveUserTypeUseCase;
    @Mock
    private UpdateUserTypeUseCase updateUserTypeUseCase;
    @Mock
    private DeleteUserTypeUseCase deleteUserTypeUseCase;

    @InjectMocks
    private UserTypeController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new UserTypeController(
                findAllUserTypesUseCase,
                findUserTypeByIdUseCase,
                saveUserTypeUseCase,
                updateUserTypeUseCase,
                deleteUserTypeUseCase
        );
    }

    @Test
    void shouldGetUserTypes() {
        var pageable = PageRequest.of(0, 10);
        var entityList = List.of(UserTypeBuilder.oneUserType().build());
        var expectedList = UserTypePresenter.userTypeToUserTypeResponseDTOList(entityList);

        when(findAllUserTypesUseCase.execute(pageable)).thenReturn(entityList);

        var result = controller.getUserTypes(pageable);

        assertEquals(expectedList, result);
        verify(findAllUserTypesUseCase, times(1)).execute(pageable);
    }

    @Test
    void shouldGetUserTypeById() {
        Long id = 1L;
        var entity = UserTypeBuilder.oneUserType().withId(id).build();
        var expected = UserTypePresenter.userTypeToUserTypeResponseDTO(entity);

        when(findUserTypeByIdUseCase.execute(id)).thenReturn(entity);

        var result = controller.getUserTypeById(id);

        assertEquals(expected, result);
        verify(findUserTypeByIdUseCase, times(1)).execute(id);
    }

    @Test
    void shouldSaveUserType() {
        var request = UserTypeRequestDTOBuilder.oneUserTypeRequestDTO().build();
        var entity = UserTypeBuilder.oneUserType().build();
        var expected = UserTypePresenter.userTypeToUserTypeResponseDTO(entity);

        when(saveUserTypeUseCase.execute(request)).thenReturn(entity);

        var result = controller.saveUserType(request);

        assertEquals(expected, result);
        verify(saveUserTypeUseCase, times(1)).execute(request);
    }

    @Test
    void shouldUpdateUserType() {
        Long id = 1L;
        var data = UserTypeUpdateDataDTOBuilder.oneUserTypeUpdateDataDTO().build();
        var request = UserTypeUpdateRequestDTOBuilder.oneUserTypeUpdateRequestDTO()
                .withId(id)
                .withData(data)
                .build();
        var entity = UserTypeBuilder.oneUserType().build();
        var expected = UserTypePresenter.userTypeToUserTypeResponseDTO(entity);

        when(updateUserTypeUseCase.execute(request)).thenReturn(entity);

        var result = controller.updateUserType(request);

        assertEquals(expected, result);
        verify(updateUserTypeUseCase, times(1)).execute(request);
    }

    @Test
    void shouldDeleteUserType() {
        Long id = 1L;

        controller.deleteUserType(id);

        verify(deleteUserTypeUseCase, times(1)).execute(id);
    }
}
