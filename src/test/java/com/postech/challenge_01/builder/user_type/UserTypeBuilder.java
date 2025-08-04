package com.postech.challenge_01.builder.user_type;

import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.domain.enums.UserTypeEnum;

import java.time.LocalDateTime;

public class UserTypeBuilder {
    private Long id = 1L;
    private String name = "nome teste";
    private UserTypeEnum type = UserTypeEnum.Owner;
    private LocalDateTime lastModifiedDateTime = LocalDateTime.now();

    public static UserTypeBuilder oneUserType(){ return new UserTypeBuilder(); }

    public UserTypeBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public UserTypeBuilder withName(String name){
        this.name = name;
        return this;
    }

    public UserTypeBuilder withType(UserTypeEnum type){
        this.type = type;
        return this;
    }

    public UserTypeBuilder withLastModifiedDateTime(LocalDateTime dateTime){
        this.lastModifiedDateTime = dateTime;
        return this;
    }

    public UserType build(){
        return new UserType(
                id,
                name,
                type,
                lastModifiedDateTime
        );
    }
}
