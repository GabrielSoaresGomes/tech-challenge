package com.postech.challenge_01.application.gateways;

import com.postech.challenge_01.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserGateway extends CrudGateway<User, Long> {
    Optional<User> findByLogin(String login);
    List<User> findByUserTypeId (Long userTypeId);
    User requireByLogin(String login);
    void updatePassword(Long id, String password);

}
