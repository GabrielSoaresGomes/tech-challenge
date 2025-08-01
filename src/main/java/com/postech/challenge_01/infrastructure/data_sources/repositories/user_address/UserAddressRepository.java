package com.postech.challenge_01.infrastructure.data_sources.repositories.user_address;
import com.postech.challenge_01.domain.UserAddress;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.CrudRepositoryDeprecated;

public interface UserAddressRepository extends CrudRepositoryDeprecated<UserAddress, Long> {
}