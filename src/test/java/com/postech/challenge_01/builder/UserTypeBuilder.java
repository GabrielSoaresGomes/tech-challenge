package com.postech.challenge_01.builder;

import com.postech.challenge_01.domains.UserType;


public class UserTypeBuilder {
    private Long id;
    private String name;

    public static UserTypeBuilder oneUserType(){ return new UserTypeBuilder(); }

    public UserTypeBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public UserTypeBuilder withName(String name){
        this.name = name;
        return this;
    }

    public UserType build(){
        return new UserType(
                id,
                name
        );
    }
}
