package com.postech.challenge_01.infrastructure.mappers;

import com.postech.challenge_01.dtos.transfer.user.NewUserDTO;
import com.postech.challenge_01.dtos.transfer.user.UserDTO;
import com.postech.challenge_01.infrastructure.entities.UserEntity;
import com.postech.challenge_01.infrastructure.entities.UserTypeEntity;

public class UserEntityMapper {
    public static UserEntity toUserEntity(UserDTO source) {
        UserTypeEntity userType = new UserTypeEntity();
        userType.setId(source.userTypeId());
        UserEntity target = new UserEntity();
        target.setId(source.id());
        target.setName(source.name());
        target.setEmail(source.email());
        target.setLogin(source.login());
        target.setPassword(source.password());
        target.setLastModifiedDateTime(source.lastModifiedDateTime());

        target.setUserType(userType);

        return target;
    }

    public static UserEntity toUserEntity(NewUserDTO source) {
        UserTypeEntity userType = new UserTypeEntity();
        userType.setId(source.userTypeId());
        UserEntity target = new UserEntity();
        target.setName(source.name());
        target.setEmail(source.email());
        target.setLogin(source.login());
        target.setPassword(source.password());

        target.setUserType(userType);

        return target;
    }

    public static UserDTO toUserDTO(UserEntity source) {
        return new UserDTO(
                source.getId(),
                source.getUserType().getId(),
                source.getName(),
                source.getEmail(),
                source.getLogin(),
                source.getPassword(),
                source.getLastModifiedDateTime()
        );
    }
}
