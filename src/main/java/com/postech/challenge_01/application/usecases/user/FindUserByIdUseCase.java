package com.postech.challenge_01.application.usecases.user;

import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindUserByIdUseCase implements UseCase<Long, User> {
    private final IUserGateway gateway;

    @Override
    public User execute(Long id) {
        log.info("Buscando usuário com ID: {}", id);
        var entity = this.gateway.findById(id);

        log.info("Usuário encontrado: {}", entity);
        return entity;
    }
}
