package com.postech.challenge_01.application.gateways;

import com.postech.challenge_01.domain.Restaurant;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestaurantGateway extends CrudGateway<Restaurant, Long> {
    List<Restaurant> findAllOpen(Pageable pageable);
}
