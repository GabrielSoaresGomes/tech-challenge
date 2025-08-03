package com.postech.challenge_01.application.usecases.menu;

import com.postech.challenge_01.application.gateways.IMenuGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.domain.Menu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindMenuByIdUseCase implements UseCase<Long, Menu> {
    private final IMenuGateway gateway;

    @Transactional
    @Override
    public Menu execute(Long id) {
        log.info("Buscando menu com ID {}", id);
        var menu = this.gateway.findById(id);

        log.info("Menu encontrado {}", id);
        return menu;
    }
}
