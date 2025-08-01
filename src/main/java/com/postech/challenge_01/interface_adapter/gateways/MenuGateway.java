package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.application.gateways.IMenuGateway;
import com.postech.challenge_01.domain.Menu;
import com.postech.challenge_01.exceptions.MenuNotFoundException;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.MenuRepository;
import com.postech.challenge_01.mappers.menu.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MenuGateway implements IMenuGateway {
    private final MenuRepository repository;

    @Override
    public Menu findById(Long id) {
        return this.requireById(id)
                .orElseThrow(() -> new MenuNotFoundException(id));
    }

    @Override
    public Optional<Menu> requireById(Long id) {
        return this.repository.findById(id)
                .map(MenuMapper::toMenu);
    }

    @Override
    public List<Menu> findAll(Pageable pageable) {
        return this.repository.findAll(pageable.getPageSize(), pageable.getOffset())
                .stream()
                .map(MenuMapper::toMenu)
                .toList();
    }

    @Override
    public Menu save(Menu menu) {
        var newMenuDTO = MenuMapper.toNewMenuDTO(menu);
        var savedMenuDTO = this.repository.save(newMenuDTO);

        return MenuMapper.toMenu(savedMenuDTO);
    }

    @Override
    public Menu update(Menu menu, Long id) {
        throw new UnsupportedOperationException("O domínio Menu não pode ser alterado.");
    }

    @Override
    public void delete(Long id) {
        var wasDeleted = this.repository.delete(id);
        if (wasDeleted == 0) {
            throw new MenuNotFoundException(id);
        }
    }
}
