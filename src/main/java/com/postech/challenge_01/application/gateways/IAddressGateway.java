package com.postech.challenge_01.application.gateways;

import com.postech.challenge_01.domain.Address;

import java.util.List;

public interface IAddressGateway extends CrudGateway<Address, Long> {
    List<Address> findAllByUserId(Long userId, int size, long offset);
    void deleteByUserId(Long userId);
    void deleteByRestaurantId(Long restaurantId);
}
