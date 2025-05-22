package com.postech.challenge_01.usecases.rules.user;

import com.postech.challenge_01.entities.User;
import com.postech.challenge_01.exceptions.InvalidEmailException;
import com.postech.challenge_01.usecases.rules.Rule;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InvalidEmailRule implements Rule<User> {
    private static final String REGEX_EMAIL =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern pattern = Pattern.compile(REGEX_EMAIL);

    @Override
    public void execute(User entity) {
        var email = entity.getEmail();

        if(email != null && !pattern.matcher(email).matches()) {
            throw new InvalidEmailException(email);
        }
    }
}
