package com.postech.challenge_01.application.gateways;

import com.postech.challenge_01.domain.UserType;

import java.util.Optional;

public interface IUserTypeGateway extends CrudGateway<UserType, Long> {
    Optional<UserType> findByName(String name);

}
