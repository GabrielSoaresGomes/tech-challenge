package com.postech.challenge_01.application.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteUserTypeUseCase implements UseCase<Long, Void> {
    private final IUserTypeGateway gateway;

    @Override
    public Void execute(Long id) {
        log.info("Deletando tipo de usuário com ID: {}", id);
        this.gateway.delete(id);

        return null;
    }
}
