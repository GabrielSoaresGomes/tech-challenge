package com.postech.challenge_01.application.usecases.menu;

import com.postech.challenge_01.dtos.responses.menu.MenuResponseDTO;
import com.postech.challenge_01.mappers.menu.MenuMapper;
import com.postech.challenge_01.infrastructure.data_sources.repositories.menu.MenuRepository;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class FindAllMenusUseCase implements UseCase<Pageable, List<MenuResponseDTO>> {
    private final MenuRepository repository;

    @Transactional
    @Override
    public List<MenuResponseDTO> execute(Pageable pageable) {
        log.info("Listando menus");
        var list = this.repository.findAll(pageable.getPageSize(), pageable.getPageNumber());

        log.info("Menus retornados {}", list);
        return MenuMapper.menusToMenuResponseDTOList(list);
    }
}
