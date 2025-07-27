package com.postech.challenge_01.usecases.menu;

import com.postech.challenge_01.domains.Menu;
import com.postech.challenge_01.dtos.requests.menu.MenuRequestDTO;
import com.postech.challenge_01.dtos.responses.menu.MenuResponseDTO;
import com.postech.challenge_01.mappers.menu.MenuMapper;
import com.postech.challenge_01.repositories.menu.MenuRepository;
import com.postech.challenge_01.usecases.UseCase;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SaveMenuUseCase implements UseCase<MenuRequestDTO, MenuResponseDTO> {
    private final MenuRepository repository;
    private final List<Rule<Menu>> rules;

    @Transactional
    @Override
    public MenuResponseDTO execute(MenuRequestDTO request) {
        var menu = MenuMapper.menuRequestDTOToMenu(request);

        this.rules.forEach(rule -> rule.execute(menu));

        log.info("Criando novo menu {}", menu);
        var savedEntity = this.repository.save(menu);

        log.info("Menu criado: {}", savedEntity);
        return MenuMapper.menuToMenuResponseDTO(savedEntity);
    }
}
