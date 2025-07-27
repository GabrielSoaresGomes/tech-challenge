package com.postech.challenge_01.usecases.menu;

import com.postech.challenge_01.dtos.responses.menu.MenuResponseDTO;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.mappers.menu.MenuMapper;
import com.postech.challenge_01.repositories.menu.MenuRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindMenuByIdUseCase implements UseCase<Long, MenuResponseDTO> {
    private final MenuRepository repository;

    @Transactional
    @Override
    public MenuResponseDTO execute(Long id) {
        log.info("Buscando menu com ID {}", id);
        var menu = this.repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu não encontrado para o id %d".formatted(id)));

        log.info("Menu encontrado {}", id);
        return MenuMapper.menuToMenuResponseDTO(menu);
    }
}
