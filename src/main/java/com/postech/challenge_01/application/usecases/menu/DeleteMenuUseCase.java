package com.postech.challenge_01.application.usecases.menu;

import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.infrastructure.data_sources.repositories.menu.MenuRepository;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeleteMenuUseCase implements UseCase<Long, Void> {
    private final MenuRepository repository;

    @Transactional
    @Override
    public Void execute(Long menuId) {
        log.info("Excluindo menu com ID {}", menuId);
        var delete = this.repository.delete(menuId);

        if (delete == 0) {
            throw new ResourceNotFoundException("Menu com ID %d não foi encontrado.".formatted(menuId));
        }

        log.info("Excluído menu com ID {}", menuId);
        return null;
    }
}
