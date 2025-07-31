package com.postech.challenge_01.infrastructure.data_sources.repositories;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.CrudRepositoryDeprecated;

import java.util.Optional;

public interface UserTypeRepository extends CrudRepositoryDeprecated<UserType, Long> {
    Optional<UserType> findByName(String name);
}
