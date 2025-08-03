package com.postech.challenge_01.infrastructure.data_sources.repositories.menu;

import com.postech.challenge_01.dtos.transfer.menu.MenuDTO;
import com.postech.challenge_01.dtos.transfer.menu.NewMenuDTO;
import com.postech.challenge_01.infrastructure.mappers.MenuEntityMapper;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
class MenuRepositoryImpl implements MenuRepository {
    private final MenuJpaRepository jpaRepository;

    @Override
    public Optional<MenuDTO> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(MenuEntityMapper::toMenuDTO);
    }

    @Override
    public List<MenuDTO> findAll(int size, long offset) {
        var pageRequest = PageRequest.of((int) offset / size, size);

        return this.jpaRepository.findAll(pageRequest)
                .stream()
                .map(MenuEntityMapper::toMenuDTO)
                .toList();
    }

    @Override
    public MenuDTO save(NewMenuDTO menu) {
        var entity = MenuEntityMapper.toMenuEntity(menu);

        var savedEntity = this.jpaRepository.save(entity);

        return MenuEntityMapper.toMenuDTO(savedEntity);
    }

    @Override
    public Optional<MenuDTO> update(MenuDTO entity) {
        throw new UnsupportedOperationException("A entidade MenuEntity não pode ser alterada.");
    }

    @Override
    public Integer delete(Long id) {
        this.jpaRepository.deleteById(id);

        return 1;
    }
}
