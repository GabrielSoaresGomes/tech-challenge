package com.postech.challenge_01.builder.user;

import com.postech.challenge_01.dtos.requests.user.UserRequestDTO;
import com.postech.challenge_01.dtos.requests.user.UserUpdateRequestDTO;

public class UserUpdateRequestDTOBuilder {
    private Long id = 1L;
    private UserRequestDTO data = UserUpdateDataDTOBuilder
            .oneUserUpdateDataDTO()
            .build();

    public static UserUpdateRequestDTOBuilder oneUserUpdateRequestDTO() {
        return new UserUpdateRequestDTOBuilder();
    }
    public UserUpdateRequestDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserUpdateRequestDTOBuilder withData(UserRequestDTO data) {
        this.data = data;
        return this;
    }

    public UserUpdateRequestDTO build() {
        return new UserUpdateRequestDTO(
                id,
                data
        );
    }
}
