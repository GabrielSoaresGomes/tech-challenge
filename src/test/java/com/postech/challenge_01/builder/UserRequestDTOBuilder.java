package com.postech.challenge_01.builder;

import com.postech.challenge_01.dtos.requests.user.UserRequestDTO;

public class UserRequestDTOBuilder {
    private String name = "Nome Teste";
    private String email = "teste@teste.com";
    private String login = "teste.teste";
    private String password = "encodedPassword123";

    public static UserRequestDTOBuilder oneUserRequestDTO() {
        return new UserRequestDTOBuilder();
    }

    public UserRequestDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserRequestDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRequestDTOBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserRequestDTOBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
    
    public UserRequestDTO build() {
        return new UserRequestDTO(name, email, login, password);
    }
}
