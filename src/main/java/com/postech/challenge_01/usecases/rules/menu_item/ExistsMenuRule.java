package com.postech.challenge_01.usecases.rules.menu_item;

import com.postech.challenge_01.domains.MenuItem;
import com.postech.challenge_01.exceptions.MenuNotFoundException;
import com.postech.challenge_01.repositories.menu.MenuRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExistsMenuRule implements Rule<MenuItem> {
    private final MenuRepository menuRepository;

    @Override
    public void execute(MenuItem menuItem) {
        var menuId = menuItem.getMenuId();

        this.menuRepository.findById(menuId)
                .orElseThrow(() -> new MenuNotFoundException(menuId));
    }
}
