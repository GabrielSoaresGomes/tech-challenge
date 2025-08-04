package com.postech.challenge_01.infrastructure.mappers;

import com.postech.challenge_01.dtos.transfer.user_type.NewUserTypeDTO;
import com.postech.challenge_01.dtos.transfer.user_type.UserTypeDTO;
import com.postech.challenge_01.infrastructure.entities.UserTypeEntity;

public class UserTypeEntityMapper {
    public static UserTypeEntity toUserTypeEntity(UserTypeDTO source) {
        UserTypeEntity target = new UserTypeEntity();
        target.setId(source.id());
        target.setName(source.name());
        target.setType(source.type());
        target.setLastModifiedDateTime(source.lastModifiedDateTime());
        return target;
    }

    public static UserTypeEntity toUserTypeEntity(NewUserTypeDTO source) {
        UserTypeEntity target = new UserTypeEntity();
        target.setName(source.name());
        target.setType(source.type());
        return target;
    }

    public static UserTypeDTO toUserTypeDTO(UserTypeEntity source) {
        return new UserTypeDTO(
                source.getId(),
                source.getName(),
                source.getType(),
                source.getLastModifiedDateTime()
        );
    }
}
