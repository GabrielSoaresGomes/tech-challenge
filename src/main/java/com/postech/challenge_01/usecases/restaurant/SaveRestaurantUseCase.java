package com.postech.challenge_01.usecases.restaurant;

import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.mappers.AddressMapper;
import com.postech.challenge_01.mappers.RestaurantMapper;
import com.postech.challenge_01.repositories.address.AddressRepository;
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
public class SaveRestaurantUseCase implements UseCase<RestaurantRequestDTO, RestaurantResponseDTO> {
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final List<Rule<Restaurant>> rules;

    @Override
    public RestaurantResponseDTO execute(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = RestaurantMapper.restaurantRequestDTOToRestaurant(restaurantRequestDTO);
        Address address = restaurant.getAddress();

        Address savedAddress = this.addressRepository.save(address);

        Restaurant restaurantWithAddress = new Restaurant(
                restaurant.getId(),
                restaurant.getOwnerId(),
                restaurant.getName(),
                restaurant.getType(),
                restaurant.getStartTime(),
                restaurant.getEndTime(),
                savedAddress
        );
        // TODO - Criar o endereço do restaurante e atrelar ele ao restaurante


        rules.forEach(rule -> rule.execute(restaurantWithAddress));

        log.info("Criando novo restaurante: {}", restaurantWithAddress);
        Restaurant savedEntity = this.restaurantRepository.save(restaurantWithAddress);

        log.info("Restaurante criado: {}", savedEntity);
        return RestaurantMapper.restaurantToRestaurantResponseDTO(savedEntity);
    }
}
