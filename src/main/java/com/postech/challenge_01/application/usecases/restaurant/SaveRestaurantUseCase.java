package com.postech.challenge_01.application.usecases.restaurant;

import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.infrastructure.data_sources.repositories.restaurant.RestaurantRepository;
import com.postech.challenge_01.mappers.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveRestaurantUseCase implements UseCase<RestaurantRequestDTO, RestaurantResponseDTO> {
    private final RestaurantRepository restaurantRepository;
    private final IAddressGateway addressGateway;
    private final List<Rule<Restaurant>> rules;

    @Override
    public RestaurantResponseDTO execute(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = RestaurantMapper.restaurantRequestDTOToRestaurant(restaurantRequestDTO);
        Address address = restaurant.getAddress();

        Address savedAddress = this.addressGateway.save(address);

        Restaurant restaurantWithAddress = new Restaurant(
                restaurant.getId(),
                restaurant.getOwnerId(),
                restaurant.getName(),
                restaurant.getType(),
                restaurant.getStartTime(),
                restaurant.getEndTime(),
                savedAddress
        );

        rules.forEach(rule -> rule.execute(restaurantWithAddress));

        log.info("Criando novo restaurante: {}", restaurantWithAddress);
        Restaurant savedEntity = this.restaurantRepository.save(restaurantWithAddress);

        log.info("Restaurante criado: {}", savedEntity);
        return RestaurantMapper.restaurantToRestaurantResponseDTO(savedEntity);
    }
}
