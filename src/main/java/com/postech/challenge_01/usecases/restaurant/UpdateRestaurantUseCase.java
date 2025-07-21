package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.exceptions.RestaurantNotFoundException;
import com.postech.challenge_01.mappers.RestaurantMapper;
import com.postech.challenge_01.repositories.restaurant.RestaurantRepository;
import com.postech.challenge_01.usecases.UseCase;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateRestaurantUseCase implements UseCase<RestaurantUpdateRequestDTO, RestaurantResponseDTO> {
    private final RestaurantRepository restaurantRepository;
    private final List<Rule<Restaurant>> rules;

    @Override
    public RestaurantResponseDTO execute(RestaurantUpdateRequestDTO request) {
        var id = request.id();
        var data = request.data();

        Restaurant entity = RestaurantMapper.restaurantRequestDTOToRestaurant(id, data);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Atualizando restaurante com ID {}: {}", id, entity);
        Restaurant updatedEntity = this.restaurantRepository.update(entity, id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        return RestaurantMapper.restaurantToRestaurantResponseDTO(updatedEntity);
    }

}
