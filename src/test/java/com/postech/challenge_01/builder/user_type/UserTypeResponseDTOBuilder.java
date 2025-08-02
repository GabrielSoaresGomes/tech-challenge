package com.postech.challenge_01.builder.user_type;

import com.postech.challenge_01.domain.enums.UserTypeEnum;
import com.postech.challenge_01.dtos.responses.user_type.UserTypeResponseDTO;

public class UserTypeResponseDTOBuilder {
    private Long id = 1L;
    private String name;
    private UserTypeEnum type;

    public static UserTypeResponseDTOBuilder oneUserTypeResponseDTO() {
        return new UserTypeResponseDTOBuilder();
    }

    public UserTypeResponseDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserTypeResponseDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserTypeResponseDTOBuilder withType(UserTypeEnum type) {
        this.type = type;
        return this;
    }

    public UserTypeResponseDTO build() {
        return new UserTypeResponseDTO(
                id,
                name,
                type
        );
    }
}
