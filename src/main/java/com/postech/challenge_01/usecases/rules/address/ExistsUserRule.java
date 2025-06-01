package com.postech.challenge_01.usecases.rules.address;

import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import com.postech.challenge_01.repositories.UserRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistsUserRule implements Rule<Address> {
    private final UserRepository userRepository;

    @Override
    public void execute(Address domain) {
        var userId = domain.getUserId();
        var user = this.userRepository.findById(userId);

        user.orElseThrow(() -> new UserNotFoundException(userId));
    }
}
