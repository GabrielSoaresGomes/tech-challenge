package com.postech.challenge_01.builder;

import com.postech.challenge_01.dtos.requests.UserTypeRequestDTO;
import com.postech.challenge_01.dtos.requests.UserTypeUpdateRequestDTO;

public class UserTypeUpdateRequestDTOBuilder {
    private Long id = 1L;
    private UserTypeRequestDTO data = UserTypeRequestDTOBuilder.oneUserTypeRequestDTO().build();

    private UserTypeUpdateRequestDTOBuilder() {}

    public static UserTypeUpdateRequestDTOBuilder oneUserTypeUpdateRequestDTO() {
        return new UserTypeUpdateRequestDTOBuilder();
    }

    public UserTypeUpdateRequestDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserTypeUpdateRequestDTOBuilder withName(String name) {
        this.data = UserTypeRequestDTOBuilder.oneUserTypeRequestDTO().withName(name).build();
        return this;
    }

    public UserTypeUpdateRequestDTO build() {
        return new UserTypeUpdateRequestDTO(id, data);
    }
}
