package com.postech.challenge_01.application.usecases.menu;

import com.postech.challenge_01.application.gateways.IMenuGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.domain.Menu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindAllMenusUseCase implements UseCase<Pageable, List<Menu>> {
    private final IMenuGateway gateway;

    @Transactional
    @Override
    public List<Menu> execute(Pageable pageable) {
        log.info("Listando menus");
        return this.gateway.findAll(pageable);
    }
}
