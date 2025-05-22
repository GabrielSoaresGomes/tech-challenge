package com.postech.challenge_01.usecases.rules.user;

import com.postech.challenge_01.entities.User;
import com.postech.challenge_01.repositories.UserRepository;
import com.postech.challenge_01.exceptions.UserAlreadyExistsException;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DuplicateLoginRule implements Rule<User> {
    private final UserRepository repository;

    @Override
    public void execute(User entity) {
        var login = entity.getLogin();
        var opUser  = repository.findByLogin(login);

        if(opUser.isEmpty()) {
            return;
        }

        if(opUser.get().getId().equals(entity.getId())) {
            return;
        }

        throw new UserAlreadyExistsException(login);
    }
}
