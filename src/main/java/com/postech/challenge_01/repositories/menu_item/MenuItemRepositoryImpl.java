package com.postech.challenge_01.repositories.menu_item;

import com.postech.challenge_01.domains.MenuItem;
import com.postech.challenge_01.entities.MenuItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
class MenuItemRepositoryImpl implements MenuItemRepository {
    private final MenuItemJpaRepository jpaRepository;

    @Override
    public Optional<MenuItem> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(MenuItemEntity::toMenuItem);
    }

    @Override
    public List<MenuItem> findAll(int size, long offset) {
        var pageRequest = PageRequest.of((int) offset / size, size);

        return this.jpaRepository.findAll(pageRequest)
                .stream()
                .map(MenuItemEntity::toMenuItem)
                .toList();
    }

    @Override
    public List<MenuItem> findAllByMenuId(Long menuId, int size, long offset) {
        var pageRequest = PageRequest.of((int) offset / size, size);

        return this.jpaRepository.findAllByMenuId(menuId, pageRequest)
                .stream()
                .map(MenuItemEntity::toMenuItem)
                .toList();
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        var entity = MenuItemEntity.of(menuItem);

        var savedEntity = this.jpaRepository.save(entity);

        return savedEntity.toMenuItem();
    }

    @Override
    public Optional<MenuItem> update(MenuItem menuItem, Long id) {
        return this.jpaRepository.findById(id)
                .map(entity -> entity.update(menuItem))
                .map(this.jpaRepository::save)
                .map(MenuItemEntity::toMenuItem);
    }

    @Override
    public Integer delete(Long id) {
        var exists = this.jpaRepository.existsById(id);
        if (!exists) return 0;

        this.jpaRepository.deleteById(id);

        return 1;
    }
}
