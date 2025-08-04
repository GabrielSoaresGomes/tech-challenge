package com.postech.challenge_01.application.usecases.rules.user_type;

import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.exceptions.UserTypeIsUsedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserTypeIsUsedRule implements Rule<UserType> {
    private final IUserGateway gateway;

    @Override
    public void execute(UserType entity) {
        var id = entity.getId();
        List<User> userList = gateway.findByUserTypeId(id);

        if(userList.isEmpty()) return;

        throw new UserTypeIsUsedException(entity.getName());
    }
}
