package com.postech.challenge_01.application.gateways;

import com.postech.challenge_01.domain.Menu;

import java.util.Optional;

public interface IMenuGateway extends CrudGateway<Menu, Long> {
    Optional<Menu> requireById(Long id);
}
