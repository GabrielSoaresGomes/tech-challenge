package com.postech.challenge_01.usecases.rules.restaurant;

import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.repositories.UserRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserIsOwnerRule implements Rule<Restaurant> {
    private final UserRepository userRepository;

    @Override
    public void execute(Restaurant domain) {
        var userId = domain.getOwnerId();
        var user = userRepository.findById(userId);

        // TODO - Verificar se o tipo do usuário é dono de restaurante
    }
}
