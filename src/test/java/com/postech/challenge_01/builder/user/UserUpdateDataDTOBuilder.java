package com.postech.challenge_01.builder.user;

import com.postech.challenge_01.dtos.requests.user.UserRequestDTO;

public class UserUpdateDataDTOBuilder {
    private Long id = 1L;
    private Long userTypeId = 1L;
    private String name = "Nome Teste";
    private String email = "teste@teste.com";
    private String login = "teste.teste";
    private String password = "encodedPassword123";

    public static UserUpdateDataDTOBuilder oneUserUpdateDataDTO(){
        return new UserUpdateDataDTOBuilder();
    }

    public UserUpdateDataDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserUpdateDataDTOBuilder withUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
        return this;
    }

    public UserUpdateDataDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserUpdateDataDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserUpdateDataDTOBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserUpdateDataDTOBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRequestDTO build() {
        return new UserRequestDTO(
                userTypeId,
                name,
                email,
                login,
                password);
    }
}
