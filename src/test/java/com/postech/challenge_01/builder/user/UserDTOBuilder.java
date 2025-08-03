package com.postech.challenge_01.builder.user;

import com.postech.challenge_01.dtos.transfer.user.UserDTO;

import java.time.LocalDateTime;

public class UserDTOBuilder {
    private Long id = 1L;
    private Long userTypeId = 1L;
    private String name = "Nome Teste";
    private String email = "teste@teste.com";
    private String login = "teste.teste";
    private String password = "encodedPassword123";
    private LocalDateTime lastModifiedDateTime = LocalDateTime.now();

    public static UserDTOBuilder oneUserDTO() {
        return new UserDTOBuilder();
    }

    public UserDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserDTOBuilder withUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
        return this;
    }

    public UserDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTOBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserDTOBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTO build() {
        return new UserDTO(
                id,
                userTypeId,
                name,
                email,
                login,
                password,
                lastModifiedDateTime
        );
    }
}
