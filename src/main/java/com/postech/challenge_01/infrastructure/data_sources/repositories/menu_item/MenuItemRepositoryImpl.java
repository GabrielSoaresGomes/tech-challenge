package com.postech.challenge_01.infrastructure.data_sources.repositories.menu_item;

import com.postech.challenge_01.dtos.transfer.menu_item.MenuItemDTO;
import com.postech.challenge_01.dtos.transfer.menu_item.NewMenuItemDTO;
import com.postech.challenge_01.infrastructure.mappers.MenuItemEntityMapper;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.MenuItemRepository;
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
    public Optional<MenuItemDTO> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(MenuItemEntityMapper::toMenuItemDTO);
    }

    @Override
    public List<MenuItemDTO> findAll(int size, long offset) {
        var pageRequest = PageRequest.of((int) offset / size, size);

        return this.jpaRepository.findAll(pageRequest)
                .stream()
                .map(MenuItemEntityMapper::toMenuItemDTO)
                .toList();
    }

    @Override
    public List<MenuItemDTO> findAllByMenuId(Long menuId, int size, long offset) {
        var pageRequest = PageRequest.of((int) offset / size, size);

        return this.jpaRepository.findAllByMenuId(menuId, pageRequest)
                .stream()
                .map(MenuItemEntityMapper::toMenuItemDTO)
                .toList();
    }

    @Override
    public MenuItemDTO save(NewMenuItemDTO dto) {
        var entity = MenuItemEntityMapper.toMenuItemEntity(dto);

        var savedEntity = this.jpaRepository.save(entity);

        return MenuItemEntityMapper.toMenuItemDTO(savedEntity);
    }

    @Override
    public Optional<MenuItemDTO> update(MenuItemDTO dto) {
        var entity = MenuItemEntityMapper.toMenuItemEntity(dto);

        var updatedEntity = this.jpaRepository.save(entity);

        return Optional.of(MenuItemEntityMapper.toMenuItemDTO(updatedEntity));
    }

    @Override
    public Integer delete(Long id) {
        this.jpaRepository.deleteById(id);

        return 1;
    }
}
