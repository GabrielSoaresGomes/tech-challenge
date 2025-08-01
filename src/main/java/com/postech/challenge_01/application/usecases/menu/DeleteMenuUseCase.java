package com.postech.challenge_01.application.usecases.menu;

import com.postech.challenge_01.application.gateways.IMenuGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeleteMenuUseCase implements UseCase<Long, Void> {
    private final IMenuGateway gateway;

    @Transactional
    @Override
    public Void execute(Long menuId) {
        log.info("Excluindo menu com ID {}", menuId);
        this.gateway.delete(menuId);

        log.info("Excluído menu com ID {}", menuId);
        return null;
    }
}
