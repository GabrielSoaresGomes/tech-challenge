package com.postech.challenge_01.usecases.rules.user;

import com.postech.challenge_01.domains.User;
import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import com.postech.challenge_01.repositories.user_type.UserTypeRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistsTypeUserRule implements Rule<User> {
    private final UserTypeRepository userTypeRepository;

    @Override
    public void execute(User domain) {
        var userTypeId = domain.getUserTypeId();
        var userType = this.userTypeRepository.findById(userTypeId);

        userType.orElseThrow(() -> new UserTypeNotFoundException(userTypeId));
    }
}
