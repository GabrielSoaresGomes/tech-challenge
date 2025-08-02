package com.postech.challenge_01.builder.user_type;

import com.postech.challenge_01.dtos.requests.user_type.FindAllUserTypesRequestDTO;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class FindAllUserTypeRequestDTOBuilder {
    private Pageable pageable = PageRequest.of(0, 10);

    public FindAllUserTypeRequestDTOBuilder oneFindAllUserTypeRequestDTO() {
        return new FindAllUserTypeRequestDTOBuilder();
    }

    public FindAllUserTypeRequestDTOBuilder withPageable(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public FindAllUserTypesRequestDTO build() {
        return new FindAllUserTypesRequestDTO(
                pageable
        );
    }

}
