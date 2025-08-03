package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.user_type.*;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeRequestDTO;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.user_type.UserTypeResponseDTO;
import com.postech.challenge_01.interface_adapter.presenters.UserTypePresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserTypeController {
    private final FindAllUserTypeUseCase findAllUserTypeUseCase;
    private final FindUserTypeByIdUseCase findUserTypeByIdUseCase;
    private final SaveUserTypeUseCase saveUserTypeUseCase;
    private final UpdateUserTypeUseCase updateUserTypeUseCase;
    private final DeleteUserTypeUseCase deleteUserTypeUseCase;

    public List<UserTypeResponseDTO> getUserTypes(PageRequest pageRequest) {
        var entityList = this.findAllUserTypeUseCase.execute(pageRequest);
        return UserTypePresenter.userTypeToUserTypeResponseDTOList(entityList);
    }

    public UserTypeResponseDTO getUserTypeById(Long id) {
        var entity = this.findUserTypeByIdUseCase.execute(id);
        return UserTypePresenter.userTypeToUserTypeResponseDTO(entity);
    }

    public UserTypeResponseDTO saveUserType(UserTypeRequestDTO userTypeRequestDTO) {
        var entity = this.saveUserTypeUseCase.execute(userTypeRequestDTO);
        return UserTypePresenter.userTypeToUserTypeResponseDTO(entity);
    }

    public UserTypeResponseDTO updateUserType(UserTypeUpdateRequestDTO userTypeUpdateRequestDTO) {
        var entity = this.updateUserTypeUseCase.execute(userTypeUpdateRequestDTO);
        return UserTypePresenter.userTypeToUserTypeResponseDTO(entity);
    }

    public void deleteUserType(Long id) {
        this.deleteUserTypeUseCase.execute(id);
    }

}
