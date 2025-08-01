package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.application.gateways.IMenuItemGateway;
import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.exceptions.MenuItemNotFoundException;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.MenuItemRepository;
import com.postech.challenge_01.mappers.meu_item.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MenuItemGateway implements IMenuItemGateway {
    private final MenuItemRepository repository;

    @Override
    public MenuItem findById(Long id) {
        return this.repository.findById(id)
                .map(MenuItemMapper::toMenuItem)
                .orElseThrow(() -> new MenuItemNotFoundException(id));
    }

    @Override
    public List<MenuItem> findAll(Pageable pageable) {
        return this.repository.findAll(pageable.getPageSize(), pageable.getOffset())
                .stream()
                .map(MenuItemMapper::toMenuItem)
                .toList();
    }

    @Override
    public List<MenuItem> findAllByMenuId(Long menuId, Pageable pageable) {
        return this.repository.findAllByMenuId(menuId, pageable.getPageSize(), pageable.getOffset())
                .stream()
                .map(MenuItemMapper::toMenuItem)
                .toList();
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        var newMenuItemDTO = MenuItemMapper.toNewMenuItemDTO(menuItem);

        var savedMenuItem = this.repository.save(newMenuItemDTO);

        return MenuItemMapper.toMenuItem(savedMenuItem);
    }

    @Override
    public MenuItem update(MenuItem menuItem, Long id) {
        var menuItemDTO = this.repository.findById(id)
                .map(entity -> MenuItemMapper.toMenuItemDTO(menuItem, entity.menuId(), id))
                .orElseThrow(() -> new MenuItemNotFoundException(id));

        var savedMenuItem = this.repository.update(menuItemDTO)
                .orElseThrow(() -> new MenuItemNotFoundException(id));

        return MenuItemMapper.toMenuItem(savedMenuItem);
    }

    @Override
    public void delete(Long id) {
        var wasDeleted = repository.delete(id);
        if (wasDeleted == 0) {
            throw new MenuItemNotFoundException(id);
        }
    }
}
