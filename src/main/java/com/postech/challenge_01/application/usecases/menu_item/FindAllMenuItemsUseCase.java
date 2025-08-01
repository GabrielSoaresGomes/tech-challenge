package com.postech.challenge_01.application.usecases.menu_item;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.domain.MenuItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindAllMenuItemsUseCase implements UseCase<Pageable, List<MenuItem>> {
    private final IMenuItemGateway gateway;

    @Transactional
    @Override
    public List<MenuItem> execute(Pageable pageable) {
        log.info("Listando itens de menu");
        var list = this.gateway.findAll(pageable);

        log.info("Itens de menu retornados {}", list);
        return list;
    }
}

