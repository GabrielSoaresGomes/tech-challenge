package com.postech.challenge_01.application.usecases.restaurant;

import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteRestaurantUseCase implements UseCase<Long, Void> {
    private final IRestaurantGateway restaurantGateway;

    @Override
    public Void execute(Long id) {

        log.info("Deletando restaurante com ID: {}", id);
        this.restaurantGateway.delete(id);

        return null;
    }
}
