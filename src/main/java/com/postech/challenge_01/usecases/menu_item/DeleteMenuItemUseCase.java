package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.repositories.menu_item.MenuItemRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeleteMenuItemUseCase implements UseCase<Long, Void> {
    private final MenuItemRepository repository;

    @Transactional
    @Override
    public Void execute(Long menuItemId) {
        log.info("Excluindo item do menu ID {}", menuItemId);
        var delete = this.repository.delete(menuItemId);

        if (delete == 0) {
            throw new ResourceNotFoundException("Item do menu com ID %d não foi encontrado.".formatted(menuItemId));
        }

        log.info("Excluído item do menu com ID {}", menuItemId);
        return null;
    }
}
