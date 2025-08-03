package com.postech.challenge_01.application.usecases.restaurant;

import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
import com.postech.challenge_01.application.mappers.AddressMapper;
import com.postech.challenge_01.application.mappers.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveRestaurantUseCase implements UseCase<RestaurantRequestDTO, Restaurant> {
    private final IRestaurantGateway restaurantGateway;
    private final IAddressGateway addressGateway;
    private final List<Rule<Restaurant>> restaurantRules;
    private final List<Rule<Address>> addressRules;

    @Override
    public Restaurant execute(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = RestaurantMapper.toRestaurant(restaurantRequestDTO);
        AddressRequestDTO addressRequestDTO = restaurantRequestDTO.address();
        Address address = AddressMapper.toAddress(addressRequestDTO);

        addressRules.forEach(rule -> rule.execute(address));

        log.info("Salvando endereço: {}", address);
        Address savedAddress = this.addressGateway.save(address);
        log.info("Endereço salvo: {}", savedAddress);

        Restaurant restaurantWithAddress = new Restaurant(
                restaurant.getId(),
                restaurant.getOwnerId(),
                restaurant.getName(),
                restaurant.getType(),
                restaurant.getStartTime(),
                restaurant.getEndTime(),
                savedAddress.getId()
        );
        log.info("Validando regras do restaurante com endereço incluso: {}", restaurantWithAddress);

        restaurantRules.forEach(rule -> rule.execute(restaurantWithAddress));

        log.info("Criando novo restaurante com endereço incluso: {}", restaurantWithAddress);
        Restaurant savedEntity = this.restaurantGateway.save(restaurantWithAddress);

        log.info("Restaurante criado com sucesso: {}", savedEntity);
        return savedEntity;
    }
}
