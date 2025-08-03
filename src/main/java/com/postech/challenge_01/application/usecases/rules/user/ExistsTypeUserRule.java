package com.postech.challenge_01.application.usecases.rules.user;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistsTypeUserRule implements Rule<User> {
    private final IUserTypeGateway gateway;

    @Override
    public void execute(User domain) {
        var userTypeId = domain.getUserTypeId();

        try {
            gateway.findById(userTypeId);
        } catch (ResourceNotFoundException e) {
            throw new UserTypeNotFoundException(userTypeId);
        }
    }
}
