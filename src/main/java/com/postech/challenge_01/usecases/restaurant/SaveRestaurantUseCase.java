package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.mappers.RestaurantMapper;
import com.postech.challenge_01.repositories.RestaurantRepository;
import com.postech.challenge_01.usecases.UseCase;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveRestaurantUseCase implements UseCase<RestaurantRequestDTO, RestaurantResponseDTO> {
    private final RestaurantRepository restaurantRepository;
    private final List<Rule<Restaurant>> rules;

    @Override
    public RestaurantResponseDTO execute(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant entity = RestaurantMapper.restaurantRequestDTOToRestaurant(restaurantRequestDTO);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Criando novo restaurante: {}", entity);
        Restaurant savedEntity = this.restaurantRepository.save(entity);

        log.info("Restaurante criado: {}", savedEntity);
        return RestaurantMapper.restaurantToRestaurantResponseDTO(savedEntity);
    }
}
