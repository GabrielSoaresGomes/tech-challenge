package com.postech.challenge_01.builder;

import com.postech.challenge_01.domains.User;

public class UserBuider {
    private Long id;
    private Long userTypeId = 1L;
    private String name = "Nome Teste";
    private String email = "teste@teste.com";
    private String login = "teste.teste";
    private String password = "encodedPassword123";

    public static UserBuider oneUser() {
        return new UserBuider();
    }

    public UserBuider withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuider withUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
        return this;
    }

    public UserBuider withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuider withEmail(String email) {
        this.email = email;
        return this;
    }

    public User build() {
        return new User(
                id,
                name,
                email,
                login,
                password);
    }
}
