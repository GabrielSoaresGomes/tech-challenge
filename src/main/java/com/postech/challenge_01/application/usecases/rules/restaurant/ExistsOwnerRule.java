package com.postech.challenge_01.application.usecases.rules.restaurant;

import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserRepository;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistsOwnerRule implements Rule<Restaurant> {
    private final UserRepository userRepository;

    @Override
    public void execute (Restaurant domain) {
        var userId = domain.getOwnerId();
        var user = this.userRepository.findById(userId);

        user.orElseThrow(() -> new UserNotFoundException(userId));
    }
}
