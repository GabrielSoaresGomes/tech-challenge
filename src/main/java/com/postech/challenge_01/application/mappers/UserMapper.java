package com.postech.challenge_01.application.mappers;

import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.dtos.transfer.user.NewUserDTO;
import com.postech.challenge_01.dtos.transfer.user.UserDTO;
import com.postech.challenge_01.dtos.requests.user.UserRequestDTO;

public class UserMapper {
    public static User toUser(
            UserRequestDTO dto,
            String passwordEncoded
    ) {
        return toUser(null, dto, passwordEncoded);
    }

    public static User toUser(
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

    public static User toUser(UserDTO target) {
        return new User(
                target.id(),
                target.userTypeId(),
                target.name(),
                target.email(),
                target.login(),
                target.password(),
                target.lastModifiedDateTime()
        );
    }

    public static UserDTO toUserDTO(User source, Long id) {
        return new UserDTO(
                id,
                source.getUserTypeId(),
                source.getName(),
                source.getEmail(),
                source.getLogin(),
                source.getPassword(),
                source.getLastModifiedDateTime()
        );
    }

    public static NewUserDTO toNewUserDTO(User source) {
        return new NewUserDTO(
                source.getUserTypeId(),
                source.getName(),
                source.getEmail(),
                source.getLogin(),
                source.getPassword()
        );
    }
}
