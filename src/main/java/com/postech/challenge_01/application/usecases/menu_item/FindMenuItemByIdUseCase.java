package com.postech.challenge_01.application.usecases.menu_item;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.domain.MenuItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindMenuItemByIdUseCase implements UseCase<Long, MenuItem> {
    private final IMenuItemGateway gateway;

    @Transactional
    @Override
    public MenuItem execute(Long id) {
        log.info("Buscando item do menu com ID {}", id);
        var menuItem = this.gateway.findById(id);

        log.info("Item do menu encontrado {}", menuItem);
        return menuItem;
    }
}
