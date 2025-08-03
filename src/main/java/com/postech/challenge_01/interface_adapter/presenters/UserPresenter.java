package com.postech.challenge_01.interface_adapter.presenters;

import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserPresenter {

    public static UserResponseDTO userToUserResponseDTO(User entity) {
        return new UserResponseDTO(entity.getId(), entity.getUserTypeId(), entity.getName(), entity.getEmail(), entity.getLogin());
    }

    public static List<UserResponseDTO> userToUserResponseDTOList(List<User> entities) {
        return entities.stream()
                .map(UserPresenter::userToUserResponseDTO)
                .collect(Collectors.toList());
    }
}
