package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.mappers.RestaurantMapper;
import com.postech.challenge_01.repositories.RestaurantRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindRestaurantByIdUseCase implements UseCase<Long, RestaurantResponseDTO> {
    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantResponseDTO execute(Long id) {
        log.info("Buscando restaurante com ID: {}", id);
        var entity = this.restaurantRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado para o id " + id));

        log.info("Restaurante encontrado: {}", entity);
        return RestaurantMapper.restaurantToRestaurantResponseDTO(entity);
    }
}
