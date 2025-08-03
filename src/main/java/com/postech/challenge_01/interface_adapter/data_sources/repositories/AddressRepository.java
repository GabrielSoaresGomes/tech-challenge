package com.postech.challenge_01.interface_adapter.data_sources.repositories;

import com.postech.challenge_01.dtos.transfer.address.AddressDTO;
import com.postech.challenge_01.dtos.transfer.address.NewAddressDTO;

import java.util.List;

public interface AddressRepository extends CrudRepository<NewAddressDTO, AddressDTO, Long> {
    List<AddressDTO> findAllByUserId(Long userId, int size, long offset);
    void deleteByUserId(Long userId);
    Integer deleteByRestaurantId(Long restaurantId);
}
