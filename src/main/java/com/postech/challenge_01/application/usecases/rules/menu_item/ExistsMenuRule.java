package com.postech.challenge_01.application.usecases.rules.menu_item;

import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.exceptions.MenuNotFoundException;
import com.postech.challenge_01.infrastructure.data_sources.repositories.menu.MenuRepository;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ExistsMenuRule implements Rule<MenuItem> {
    private final MenuRepository menuRepository;

    @Override
    public void execute(MenuItem menuItem) {
        var menuId = menuItem.getMenuId();

        if (Objects.isNull(menuId)) return;

        this.menuRepository.findById(menuId)
                .orElseThrow(() -> new MenuNotFoundException(menuId));
    }
}
