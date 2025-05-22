package com.postech.challenge_01.mappers;

import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.entities.User;

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
                dto.name(),
                dto.email(),
                dto.login(),
                passwordEncoded,
                dto.address()
        );
    }

    public static UserResponseDTO userToUserResponseDTO(User entity) {
        return new UserResponseDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getLogin(), entity.getAddress());
    }

    public static List<UserResponseDTO> userToUserResponseDTOList(List<User> entities) {
        return entities.stream()
                .map(UserMapper::userToUserResponseDTO)
                .collect(Collectors.toList());
    }
}
