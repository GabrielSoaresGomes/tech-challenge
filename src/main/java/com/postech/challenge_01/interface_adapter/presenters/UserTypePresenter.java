package com.postech.challenge_01.interface_adapter.presenters;

import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.dtos.responses.user_type.UserTypeResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserTypePresenter {

    public static UserTypeResponseDTO userTypeToUserTypeResponseDTO(UserType entity) {
        return new UserTypeResponseDTO(entity.getId(), entity.getName(), entity.getType());
    }

    public static List<UserTypeResponseDTO> userTypeToUserTypeResponseDTOList(List<UserType> entities) {
        return entities.stream()
                .map(UserTypePresenter::userTypeToUserTypeResponseDTO)
                .collect(Collectors.toList());
    }
}
