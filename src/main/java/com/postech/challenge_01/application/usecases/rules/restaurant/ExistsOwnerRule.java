package com.postech.challenge_01.application.usecases.rules.restaurant;

import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserRepository;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.interface_adapter.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistsOwnerRule implements Rule<Restaurant> {
    private final UserGateway userGateway;

    @Override
    public void execute (Restaurant domain) {
        var userId = domain.getOwnerId();
        this.userGateway.findById(userId);
    }
}
