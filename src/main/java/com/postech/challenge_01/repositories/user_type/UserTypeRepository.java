package com.postech.challenge_01.repositories.user_type;
import com.postech.challenge_01.domains.UserType;
import com.postech.challenge_01.repositories.CrudRepository;

import java.util.Optional;

public interface UserTypeRepository extends CrudRepository<UserType, Long> {
    Optional<UserType> findByName(String name);
}
