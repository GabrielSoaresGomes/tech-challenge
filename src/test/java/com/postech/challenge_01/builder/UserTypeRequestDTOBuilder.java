package com.postech.challenge_01.builder;


import com.postech.challenge_01.domains.enums.UserTypeEnum;
import com.postech.challenge_01.dtos.requests.UserTypeRequestDTO;

public class UserTypeRequestDTOBuilder {
    private String name = "Nome";
    private UserTypeEnum type = UserTypeEnum.Owner;


    public static UserTypeRequestDTOBuilder oneUserTypeRequestDTO() { return new UserTypeRequestDTOBuilder(); }

    public UserTypeRequestDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserTypeRequestDTOBuilder withType(UserTypeEnum type) {
        this.type = type;
        return this;
    }

    public UserTypeRequestDTO build() {
        return new UserTypeRequestDTO(name, type);
    }

}
