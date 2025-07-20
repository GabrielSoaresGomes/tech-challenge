package com.postech.challenge_01.builder;


import com.postech.challenge_01.dtos.requests.UserTypeRequestDTO;

public class UserTypeRequestDTOBuilder {
    private String name = "Nome";

    public static UserTypeRequestDTOBuilder oneUserTypeRequestDTO() { return new UserTypeRequestDTOBuilder(); }

    public UserTypeRequestDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserTypeRequestDTO build() {
        return new UserTypeRequestDTO(name);
    }

}
