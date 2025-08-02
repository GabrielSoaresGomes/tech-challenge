package com.postech.challenge_01.application.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.domain.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindUserTypeByIdUseCase implements UseCase<Long, UserType> {
    private final IUserTypeGateway gateway;

    @Override
    public UserType execute(Long id) {
        log.info("Buscando tipo de usuário com ID: {}", id);
        var entity = this.gateway.findById(id);

        log.info("Tipo de usuário encontrado: {}", entity);
        return entity;
    }
}
