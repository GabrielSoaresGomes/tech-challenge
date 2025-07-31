package com.postech.challenge_01.infrastructure.data_sources.repositories.address;

import com.postech.challenge_01.dtos.transfer.address.AddressDTO;
import com.postech.challenge_01.dtos.transfer.address.NewAddressDTO;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<NewAddressDTO, AddressDTO, Long> {
    List<AddressDTO> findAllByUserId(Long userId, int size, long offset);
    Optional<AddressDTO> findByIdAndUserId(Long id, Long userId);
    void deleteByUserId(Long userId);
}
