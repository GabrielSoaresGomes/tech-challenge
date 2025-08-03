package com.postech.challenge_01.application.usecases.restaurant;

import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllRestaurantsUseCase implements UseCase<Pageable, List<Restaurant>> {
    private final IRestaurantGateway gateway;

    public List<Restaurant> execute(Pageable pageable) {
        log.info("Listando todos restaurantes");
        return this.gateway.findAll(pageable);
    }
}
