package com.postech.challenge_01.repositories.restaurant;

import com.postech.challenge_01.domains.Restaurant;
import com.postech.challenge_01.repositories.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAllOpen(int size, long offset);
}
