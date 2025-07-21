package com.postech.challenge_01.usecases.rules.menu;

import com.postech.challenge_01.domains.menu.Menu;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExistsRestaurantRule implements Rule<Menu> {
    @Override
    public void execute(Menu entity) {
        // TODO: ImplementRule
    }
}
