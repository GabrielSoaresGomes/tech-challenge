package com.postech.challenge_01.usecases.rules.user_type;

import com.postech.challenge_01.domains.UserType;
import com.postech.challenge_01.usecases.rules.Rule;
import org.springframework.stereotype.Component;

@Component
public class UserTypeNameRequiredRule implements Rule<UserType> {

    @Override
    public void execute(UserType entity) {
        var name = entity.getName();

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do tipo de usuário é obrigatório.");
        }
    }
}
