package com.postech.challenge_01.application.usecases.rules.user_type;

import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.exceptions.UserTypeAlreadyExistsException;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.interface_adapter.gateways.UserTypeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DuplicateUserTypeNameRule implements Rule<UserType> {
    private final UserTypeGateway gateway;

    @Override
    public void execute(UserType entity) {
        var name = entity.getName();
        Optional<UserType> optionalUserType = gateway.findByName(name);

        if(optionalUserType.isEmpty()) return;

        if(optionalUserType.get().getId().equals(entity.getId())) return;

        throw new UserTypeAlreadyExistsException(name);
    }
}
