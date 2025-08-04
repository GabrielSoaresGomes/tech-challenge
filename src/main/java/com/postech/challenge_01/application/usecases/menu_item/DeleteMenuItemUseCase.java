package com.postech.challenge_01.application.usecases.menu_item;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeleteMenuItemUseCase implements UseCase<Long, Void> {
    private final IMenuItemGateway gateway;

    @Transactional
    @Override
    public Void execute(Long menuItemId) {
        log.info("Excluindo item do menu ID {}", menuItemId);
        this.gateway.delete(menuItemId);

        log.info("Excluído item do menu com ID {}", menuItemId);
        return null;
    }
}
