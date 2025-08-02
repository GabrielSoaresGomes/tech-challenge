package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.application.gateways.IRestaurantGateway;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.RestaurantRepository;
import com.postech.challenge_01.mappers.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantGateway implements IRestaurantGateway {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant findById(Long id) {
        var restaurantDTO = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado para o id " + id));
        return RestaurantMapper.toRestaurant(restaurantDTO);
    }

    @Override
    public List<Restaurant> findAll(Pageable pageable) {
        return restaurantRepository.findAll(pageable.getPageSize(), pageable.getOffset())
                .stream()
                .map(RestaurantMapper::toRestaurant)
                .toList();
    }

    @Override
    public List<Restaurant> findAllOpen(Pageable pageable) {
        return restaurantRepository.findAllOpen(pageable.getPageSize(), pageable.getOffset())
                .stream()
                .map(RestaurantMapper::toRestaurant)
                .toList();
    }

    @Override
    public Restaurant save(Restaurant entity) {
        var newRestaurantDTO = RestaurantMapper.toNewRestaurantDTO(entity);
        var savedRestaurantDTO = restaurantRepository.save(newRestaurantDTO);
        return RestaurantMapper.toRestaurant(savedRestaurantDTO);
    }

    @Override
    public Restaurant update(Restaurant entity, Long id) {
        var restaurantDTO = RestaurantMapper.toRestaurantDTO(entity, id);
        var savedRestaurantDTO = restaurantRepository.update(restaurantDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado para o id " + id));
        return RestaurantMapper.toRestaurant(savedRestaurantDTO);
    }

    @Override
    public void delete(Long id) {
        var wasDeleted = restaurantRepository.delete(id);
        if (wasDeleted == 0) {
            throw new ResourceNotFoundException("Restaurante não encontrado para o id " + id);
        }
    }
}
