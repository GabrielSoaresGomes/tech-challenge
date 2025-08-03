package com.postech.challenge_01.interface_adapter.data_sources.repositories;

import com.postech.challenge_01.dtos.transfer.restaurant.NewRestaurantDTO;
import com.postech.challenge_01.dtos.transfer.restaurant.RestaurantDTO;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<NewRestaurantDTO, RestaurantDTO, Long> {
    List<RestaurantDTO> findAllOpen(int size, long offset);
}
