package com.postech.challenge_01.builder.user;

import com.postech.challenge_01.dtos.requests.user.FindAllUsersRequestDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class FindAllUserRequestDTOBuilder {
    private Pageable pageable = PageRequest.of(0, 10);

    public FindAllUserRequestDTOBuilder oneFindAllUserRequestDTO() {
        return new FindAllUserRequestDTOBuilder();
    }

    public FindAllUserRequestDTOBuilder withPageable(Pageable pageable){
        this.pageable = pageable;
        return this;
    }

    public FindAllUsersRequestDTO build() {
        return new FindAllUsersRequestDTO(
                pageable
        );
    }

}
