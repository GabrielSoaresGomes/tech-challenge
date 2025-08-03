package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteAddressUseCase implements UseCase<Long, Void> {
    private final IAddressGateway gateway;

    @Override
    public Void execute(Long id) {
        log.info("Deletando endereço com ID: {}", id);
        this.gateway.delete(id);

        return null;
    }
}

