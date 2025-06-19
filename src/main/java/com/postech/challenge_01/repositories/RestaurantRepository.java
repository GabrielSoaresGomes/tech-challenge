package com.postech.challenge_01.repositories;

import com.postech.challenge_01.domains.Restaurant;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAllOpen(int size, long offset);
}
