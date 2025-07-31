package com.postech.challenge_01.application.usecases.rules.menu;

import com.postech.challenge_01.domain.Menu;
import com.postech.challenge_01.exceptions.RestaurantNotFoundException;
import com.postech.challenge_01.infrastructure.data_sources.repositories.restaurant.RestaurantRepository;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExistsRestaurantRule implements Rule<Menu> {
    private final RestaurantRepository restaurantRepository;

    @Override
    public void execute(Menu entity) {
        var restaurantId = entity.getRestaurantId();

        this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }
}
