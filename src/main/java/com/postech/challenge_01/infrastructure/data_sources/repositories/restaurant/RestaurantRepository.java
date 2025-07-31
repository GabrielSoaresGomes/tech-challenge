package com.postech.challenge_01.infrastructure.data_sources.repositories.restaurant;

import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.CrudRepositoryDeprecated;

import java.util.List;

public interface RestaurantRepository extends CrudRepositoryDeprecated<Restaurant, Long> {
    List<Restaurant> findAllOpen(int size, long offset);
}
