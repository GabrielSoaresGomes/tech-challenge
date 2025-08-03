package com.postech.challenge_01.interface_adapter.data_sources.repositories;

import com.postech.challenge_01.dtos.transfer.address.AddressDTO;
import com.postech.challenge_01.dtos.transfer.address.NewAddressDTO;

public interface AddressRepository extends CrudRepository<NewAddressDTO, AddressDTO, Long> {
}
