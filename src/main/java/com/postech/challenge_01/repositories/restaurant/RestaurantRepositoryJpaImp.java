package com.postech.challenge_01.repositories.restaurant;

import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.entities.RestaurantEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantRepositoryJpaImp implements RestaurantRepository{
    private final RestaurantJpaRepository jpaRepository;

    public RestaurantRepositoryJpaImp(RestaurantJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(RestaurantEntity::toRestaurant);
    }

    @Override
    public List<Restaurant> findAll(int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        return this.jpaRepository.findAll(pageable)
                .stream()
                .map(RestaurantEntity::toRestaurant)
                .toList();
    }

    @Override
    public List<Restaurant> findAllOpen(int size, long offset) {
        Pageable pageable = PageRequest.of((int) offset, size);
        LocalTime now = LocalTime.now();

        return this.jpaRepository.findAllOpen(now, pageable)
                .stream()
                .map(RestaurantEntity::toRestaurant)
                .toList();
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity savedEntity = jpaRepository.save(RestaurantEntity.of(restaurant));
        return savedEntity.toRestaurant();
    }

    @Override
    public Optional<Restaurant> update(Restaurant restaurant, Long id) {
        if (!jpaRepository.existsById(id)) return Optional.empty();

        RestaurantEntity entityToUpdate = RestaurantEntity.of(restaurant);
        entityToUpdate.setId(id);

        RestaurantEntity updatedEntity = jpaRepository.save(entityToUpdate);
        return Optional.of(updatedEntity.toRestaurant());
    }

    @Override
    public Integer delete(Long id) {
        if (!jpaRepository.existsById(id)) return 0;
        jpaRepository.deleteById(id);
        return 1;
    }
}
