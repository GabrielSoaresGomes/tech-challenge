package com.postech.challenge_01.builder.user_type;

import com.postech.challenge_01.domain.enums.UserTypeEnum;
import com.postech.challenge_01.dtos.transfer.user_type.UserTypeDTO;

import java.time.LocalDateTime;

public class UserTypeDTOBuilder {
    private Long id = 1L;
    private String name = "nome teste";
    private UserTypeEnum type = UserTypeEnum.Owner;
    private LocalDateTime lastModifiedDateTime = LocalDateTime.now();

    public static UserTypeDTOBuilder oneUserTypeDTO(){ return new UserTypeDTOBuilder(); }

    public UserTypeDTOBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public UserTypeDTOBuilder withName(String name){
        this.name = name;
        return this;
    }

    public UserTypeDTOBuilder withType(UserTypeEnum type){
        this.type = type;
        return this;
    }

    public UserTypeDTOBuilder withLastModifiedDateTime(LocalDateTime dateTime){
        this.lastModifiedDateTime = dateTime;
        return this;
    }

    public UserTypeDTO build(){
        return new UserTypeDTO(
                id,
                name,
                type,
                lastModifiedDateTime
        );
    }
}
