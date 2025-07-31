package com.postech.challenge_01.application.usecases.rules.user;

import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import com.postech.challenge_01.infrastructure.data_sources.repositories.UserTypeRepository;
import com.postech.challenge_01.application.usecases.rules.Rule;
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
