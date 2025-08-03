package com.postech.challenge_01.builder.user;

import com.postech.challenge_01.builder.user_type.UserTypeResponseDTOBuilder;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;

public class UserResponseDTOBuilder {
    private Long id;
    private Long userTypeId = 1L;
    private String name = "Nome Teste";
    private String email = "teste@teste.com";
    private String login = "teste.teste";
    private String password = "encodedPassword123";

    public static UserTypeResponseDTOBuilder oneUserResponseDTO() {
        return new UserTypeResponseDTOBuilder();
    }

    public UserResponseDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserResponseDTOBuilder withUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
        return this;
    }

    public UserResponseDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserResponseDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserResponseDTOBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserResponseDTOBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserResponseDTO build() {
        return new UserResponseDTO(
                id,
                userTypeId,
                name,
                email,
                login
        );
    }
}
