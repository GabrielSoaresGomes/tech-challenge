package com.postech.challenge_01.usecases.menu_item;

import com.postech.challenge_01.dtos.common.ImageDto;
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
public class FindPlatePhotoMenuItemByIdUseCase implements UseCase<Long, ImageDto> {
    private final MenuItemRepository repository;

    @Transactional
    @Override
    public ImageDto execute(Long id) {
        log.info("Buscando item do menu com ID {}", id);
        var menuItem = this.repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item do menu não encontrado para o id %d".formatted(id)));

        log.info("Item do menu encontrado {}", id);
        return new ImageDto(
                menuItem.getPlatePhotoContent(),
                menuItem.getPlatePhotoOriginalFilename(),
                menuItem.getPlatePhotoMimeType()
        );
    }
}
