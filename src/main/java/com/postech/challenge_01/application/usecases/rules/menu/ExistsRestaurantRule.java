package com.postech.challenge_01.application.usecases.rules.menu;

import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import com.postech.challenge_01.domain.Menu;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExistsRestaurantRule implements Rule<Menu> {
    private final IRestaurantGateway restaurantGateway;

    @Override
    public void execute(Menu entity) {
        var restaurantId = entity.getRestaurantId();

        this.restaurantGateway.findById(restaurantId);
    }
}
