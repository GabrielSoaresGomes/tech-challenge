package com.postech.challenge_01.application.usecases.user;

import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteUserUseCase implements UseCase<Long, Void> {
    private final IUserGateway userGateway;
    private final IAddressGateway addressGateway;

    @Override
    public Void execute(Long id) {
        log.info("Deletando endereços do usuário com ID: {}", id);
        this.addressGateway.deleteByUserId(id);

        log.info("Deletando usuário com ID: {}", id);
        this.userGateway.delete(id);

        return null;
    }
}

