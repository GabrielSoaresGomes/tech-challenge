package com.postech.challenge_01.builder.user;

import com.postech.challenge_01.dtos.transfer.user.NewUserDTO;

public class NewUserDTOBuilder {
    private Long userTypeId = 1L;
    private String name = "Nome Teste";
    private String email = "teste@teste.com";
    private String login = "teste.teste";
    private String password = "encodedPassword123";

    public static NewUserDTOBuilder oneNewUserDTO() {
        return new NewUserDTOBuilder();
    }

    public NewUserDTOBuilder withUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
        return this;
    }

    public NewUserDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public NewUserDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public NewUserDTOBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public NewUserDTOBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
    
    public NewUserDTO build() {
        return new NewUserDTO(userTypeId, name, email, login, password);
    }
}
