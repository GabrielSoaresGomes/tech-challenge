package com.postech.challenge_01.application.usecases.rules.user_type;

import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.exceptions.UserTypeAlreadyExistsException;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.infrastructure.data_sources.repositories.user_type.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DuplicateUserTypeNameRule implements Rule<UserType> {
    private final UserTypeRepository repository;

    @Override
    public void execute(UserType entity) {
        var name = entity.getName();
        Optional<UserType> optionalUserType = repository.findByName(name);

        if(optionalUserType.isEmpty()) return;

        if(optionalUserType.get().getId().equals(entity.getId())) return;

        throw new UserTypeAlreadyExistsException(name);
    }
}
