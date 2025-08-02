package com.postech.challenge_01.application.usecases.menu;

import com.postech.challenge_01.application.gateways.IMenuGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.Menu;
import com.postech.challenge_01.dtos.requests.menu.MenuRequestDTO;
import com.postech.challenge_01.mappers.MenuMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SaveMenuUseCase implements UseCase<MenuRequestDTO, Menu> {
    private final IMenuGateway gateway;
    private final List<Rule<Menu>> rules;

    @Transactional
    @Override
    public Menu execute(MenuRequestDTO request) {
        var menu = MenuMapper.toMenu(request);

        this.rules.forEach(rule -> rule.execute(menu));

        log.info("Criando novo menu {}", menu);
        var savedEntity = this.gateway.save(menu);

        log.info("Menu criado: {}", savedEntity);
        return savedEntity;
    }
}
