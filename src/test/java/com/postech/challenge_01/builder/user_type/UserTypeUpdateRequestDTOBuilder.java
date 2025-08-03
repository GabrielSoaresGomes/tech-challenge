package com.postech.challenge_01.builder.user_type;

import com.postech.challenge_01.dtos.requests.user_type.UserTypeUpdateDataDTO;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeUpdateRequestDTO;

public class UserTypeUpdateRequestDTOBuilder {
    private Long id = 1L;
    private UserTypeUpdateDataDTO data = UserTypeUpdateDataDTOBuilder
            .oneUserTypeUpdateDataDTO()
            .build();

    public static UserTypeUpdateRequestDTOBuilder oneUserTypeUpdateRequestDTO() {
        return new UserTypeUpdateRequestDTOBuilder();
    }

    public UserTypeUpdateRequestDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserTypeUpdateRequestDTOBuilder withData(UserTypeUpdateDataDTO data) {
        this.data = data;
        return this;
    }

    public UserTypeUpdateRequestDTO build() {
        return new UserTypeUpdateRequestDTO(
                id,
                data
        );
    }
}
