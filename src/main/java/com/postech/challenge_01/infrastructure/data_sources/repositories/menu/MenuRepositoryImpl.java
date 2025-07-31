package com.postech.challenge_01.infrastructure.data_sources.repositories.menu;

import com.postech.challenge_01.domain.Menu;
import com.postech.challenge_01.infrastructure.entities.MenuEntity;
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
    public Optional<Menu> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(MenuEntity::toMenu);
    }

    @Override
    public List<Menu> findAll(int size, long offset) {
        var pageRequest = PageRequest.of((int) offset / size, size);

        return this.jpaRepository.findAll(pageRequest)
                .stream()
                .map(MenuEntity::toMenu)
                .toList();
    }

    @Override
    public Menu save(Menu menu) {
        var entity = MenuEntity.of(menu);

        var savedEntity = this.jpaRepository.save(entity);

        return savedEntity.toMenu();
    }

    @Override
    public Optional<Menu> update(Menu menu, Long id) {
        throw new UnsupportedOperationException("A entidade MenuEntity não pode ser alterada.");
    }

    @Override
    public Integer delete(Long id) {
        var exists = this.jpaRepository.existsById(id);
        if (!exists) {
            return 0;
        }

        this.jpaRepository.deleteById(id);

        return 1;
    }
}
