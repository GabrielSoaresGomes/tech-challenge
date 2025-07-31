package com.postech.challenge_01.application.usecases.rules.user;

import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.exceptions.UserAlreadyExistsException;
import com.postech.challenge_01.interface_adapter.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DuplicateLoginRule implements Rule<User> {
    private final UserGateway userGateway;

    @Override
    public void execute(User entity) {
        var login = entity.getLogin();
        var opUser  = userGateway.findByLogin(login);

        if(opUser.isEmpty()) {
            return;
        }

        if(opUser.get().getId().equals(entity.getId())) {
            return;
        }

        throw new UserAlreadyExistsException(login);
    }
}
