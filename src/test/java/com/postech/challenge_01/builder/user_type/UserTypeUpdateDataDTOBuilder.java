package com.postech.challenge_01.builder.user_type;

import com.postech.challenge_01.domain.enums.UserTypeEnum;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeUpdateDataDTO;

public class UserTypeUpdateDataDTOBuilder {
    private String name = "Nome atualizado";
    private UserTypeEnum type = UserTypeEnum.Client;

    public static UserTypeUpdateDataDTOBuilder oneUserTypeUpdateDataDTO() {
        return new UserTypeUpdateDataDTOBuilder();
    }

    public UserTypeUpdateDataDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserTypeUpdateDataDTOBuilder withType(UserTypeEnum type) {
        this.type = type;
        return this;
    }

    public UserTypeUpdateDataDTO build() {
        return new UserTypeUpdateDataDTO(
                name,
                type
        );
    }
}
