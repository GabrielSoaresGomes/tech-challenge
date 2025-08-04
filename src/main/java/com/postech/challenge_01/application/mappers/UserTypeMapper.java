package com.postech.challenge_01.application.mappers;

import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeRequestDTO;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeUpdateDataDTO;
import com.postech.challenge_01.dtos.transfer.user_type.NewUserTypeDTO;
import com.postech.challenge_01.dtos.transfer.user_type.UserTypeDTO;

public class UserTypeMapper {
    public static UserType toUserType(
            UserTypeRequestDTO dto
    ) {
        return toUserType(null, dto);
    }

    public static UserType toUserType(
            Long id,
            UserTypeRequestDTO dto
    ) {
        return new UserType(
                id,
                dto.name(),
                dto.type()
        );
    }

    public static UserType toUserType(
            Long id,
            UserTypeUpdateDataDTO dto
    ) {
        return new UserType(
                id,
                dto.name(),
                dto.type()
        );
    }

    public static UserType toUserType(UserTypeDTO target) {
        return new UserType(
                target.id(),
                target.name(),
                target.type(),
                target.lastModifiedDateTime()
        );
    }

    public static UserTypeDTO toUserTypeDTO(UserType source, Long id) {
        return new UserTypeDTO(
                id,
                source.getName(),
                source.getType(),
                source.getLastModifiedDateTime()
        );
    }

    public static NewUserTypeDTO toNewUserTypeDTO(UserType source) {
        return new NewUserTypeDTO(
                source.getName(),
                source.getType()
        );
    }
}
