package com.postech.challenge_01.interface_adapter.data_sources.repositories;

import com.postech.challenge_01.dtos.transfer.user_type.NewUserTypeDTO;
import com.postech.challenge_01.dtos.transfer.user_type.UserTypeDTO;

import java.util.Optional;

public interface UserTypeRepository extends CrudRepository<NewUserTypeDTO, UserTypeDTO, Long> {
    Optional<UserTypeDTO> findByName(String name);
}
