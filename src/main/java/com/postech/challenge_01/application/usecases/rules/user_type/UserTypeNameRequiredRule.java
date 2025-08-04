package com.postech.challenge_01.application.usecases.rules.user_type;

import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.exceptions.UserTypeNameRequiredException;
import com.postech.challenge_01.application.usecases.rules.Rule;
import org.springframework.stereotype.Component;

@Component
public class UserTypeNameRequiredRule implements Rule<UserType> {

    @Override
    public void execute(UserType entity) {
        var name = entity.getName();

        if (name == null || name.trim().isEmpty()) {
            throw new UserTypeNameRequiredException();
        }
    }
}
