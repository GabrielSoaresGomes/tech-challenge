package com.postech.challenge_01.builder;

import com.postech.challenge_01.domains.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@SuppressWarnings({"unused"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBuilder {
    private Long id;
    private Long userTypeId = 1L;
    private String name = "Nome Teste";
    private String email = "teste@teste.com";
    private String login = "teste.teste";
    private String password = "encodedPassword123";

    public static UserBuilder oneUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        return new User(
                id,
                userTypeId,
                name,
                email,
                login,
                password);
    }
}
