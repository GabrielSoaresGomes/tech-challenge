package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.repositories.restaurant.RestaurantRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteRestaurantUseCase implements UseCase<Long, Void> {
    private final RestaurantRepository restaurantRepository;

    @Override
    public Void execute(Long id) {
        // TODO - Apagar o endereço do restaurante
        log.info("Deletando restaurante com ID: {}", id);
        Integer delete = this.restaurantRepository.delete(id);

        if (delete == 0) {
            throw new ResourceNotFoundException("Restaurante com ID " + id + " não foi encontrado");
        }

        return null;
    }
}
