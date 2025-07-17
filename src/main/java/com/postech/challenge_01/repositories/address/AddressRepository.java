package com.postech.challenge_01.repositories.address;

import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAllByUserId(Long userId, int size, long offset);
    Optional<Address> findByIdAndUserId(Long id, Long userId);
    void deleteByUserId(Long userId);
}
