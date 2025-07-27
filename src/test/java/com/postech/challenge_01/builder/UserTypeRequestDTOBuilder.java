package com.postech.challenge_01.builder;


import com.postech.challenge_01.dtos.requests.UserTypeRequestDTO;

public class UserTypeRequestDTOBuilder {
    private String name = "Nome";
    private String type = "Tipo";


    public static UserTypeRequestDTOBuilder oneUserTypeRequestDTO() { return new UserTypeRequestDTOBuilder(); }

    public UserTypeRequestDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserTypeRequestDTOBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public UserTypeRequestDTO build() {
        return new UserTypeRequestDTO(name, type);
    }

}
