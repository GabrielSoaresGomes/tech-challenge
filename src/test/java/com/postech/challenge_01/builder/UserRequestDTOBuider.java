package com.postech.challenge_01.builder;

import com.postech.challenge_01.dtos.requests.UserRequestDTO;

public class UserRequestDTOBuider {
    private String name = "Nome Teste";
    private String email = "teste@teste.com";
    private String login = "teste.teste";
    private String password = "encodedPassword123";

    public static UserRequestDTOBuider oneUserRequestDTO() {
        return new UserRequestDTOBuider();
    }

    public UserRequestDTOBuider withName(String name) {
        this.name = name;
        return this;
    }

    public UserRequestDTOBuider withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRequestDTOBuider withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserRequestDTOBuider withPassword(String password) {
        this.password = password;
        return this;
    }
    
    public UserRequestDTO build() {
        return new UserRequestDTO(name, email, login, password);
    }
}
