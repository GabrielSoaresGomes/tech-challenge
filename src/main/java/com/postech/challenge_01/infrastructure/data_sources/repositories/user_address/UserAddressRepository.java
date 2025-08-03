package com.postech.challenge_01.infrastructure.data_sources.repositories.user_address;

import com.postech.challenge_01.dtos.transfer.user_address.NewUserAddressDTO;
import com.postech.challenge_01.dtos.transfer.user_address.UserAddressDTO;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.CrudRepository;

public interface UserAddressRepository extends CrudRepository<NewUserAddressDTO, UserAddressDTO, Long> {
}