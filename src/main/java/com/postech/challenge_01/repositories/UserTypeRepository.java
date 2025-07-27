package com.postech.challenge_01.repositories;
import com.postech.challenge_01.domains.UserType;

import java.util.Optional;

public interface UserTypeRepository extends CrudRepository<UserType, Long> {
    Optional<UserType> findByName(String name);
}
