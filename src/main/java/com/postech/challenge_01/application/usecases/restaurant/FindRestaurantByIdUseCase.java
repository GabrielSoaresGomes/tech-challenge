package com.postech.challenge_01.application.usecases.restaurant;

import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindRestaurantByIdUseCase implements UseCase<Long, Restaurant> {
    private final IRestaurantGateway gateway;

    @Override
    public Restaurant execute(Long id) {
        log.info("Buscando restaurante com ID: {}", id);
        var entity = this.gateway.findById(id);

        log.info("Restaurante encontrado: {}", entity);
        return entity;
    }
}
