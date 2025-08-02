package com.postech.challenge_01.application.usecases.restaurant;

import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateRequestDTO;
import com.postech.challenge_01.mappers.RestaurantMapper;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateRestaurantUseCase implements UseCase<RestaurantUpdateRequestDTO, Restaurant> {
    private final IRestaurantGateway gateway;
    private final List<Rule<Restaurant>> rules;

    @Override
    public Restaurant execute(RestaurantUpdateRequestDTO request) {
        var id = request.id();
        var data = request.data();

        Restaurant restaurant = RestaurantMapper.toRestaurant(id, data);

        rules.forEach(rule -> rule.execute(restaurant));

        log.info("Atualizando restaurante com ID {}: {}", id, restaurant);
        return this.gateway.update(restaurant, id);
    }

}
