package com.postech.challenge_01.mappers;

import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.domains.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User userRequestDTOToUser(
            UserRequestDTO dto,
            String passwordEncoded
    ) {
        return userRequestDTOToUser(null, dto, passwordEncoded);
    }

    public static User userRequestDTOToUser(
            Long id,
            UserRequestDTO dto,
            String passwordEncoded
    ) {
        return new User(
                id,
                dto.userTypeId(),
                dto.name(),
                dto.email(),
                dto.login(),
                passwordEncoded
        );
    }

    public static UserResponseDTO userToUserResponseDTO(User entity) {
        return new UserResponseDTO(entity.getId(), entity.getUserTypeId(), entity.getName(), entity.getEmail(), entity.getLogin());
    }

    public static List<UserResponseDTO> userToUserResponseDTOList(List<User> entities) {
        return entities.stream()
                .map(UserMapper::userToUserResponseDTO)
                .collect(Collectors.toList());
    }
}
