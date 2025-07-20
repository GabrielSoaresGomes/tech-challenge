package com.postech.challenge_01.usecases.rules.user_type;

import com.postech.challenge_01.domains.UserType;
import com.postech.challenge_01.exceptions.UserTypeAlreadyExistsException;
import com.postech.challenge_01.repositories.UserTypeRepository;
import com.postech.challenge_01.usecases.rules.Rule;
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
