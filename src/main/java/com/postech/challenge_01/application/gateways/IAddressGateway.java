package com.postech.challenge_01.application.gateways;

import com.postech.challenge_01.domain.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressGateway extends CrudGateway<Address, Long> {
    List<Address> findAllByUserId(Long userId, int size, long offset);
    Optional<Address> findByIdAndUserId(Long id, Long userId);
    void deleteByUserId(Long userId);
    void deleteByRestaurantId(Long restaurantId);
}
