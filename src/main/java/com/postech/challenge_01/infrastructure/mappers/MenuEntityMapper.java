package com.postech.challenge_01.infrastructure.mappers;

import com.postech.challenge_01.dtos.transfer.menu.MenuDTO;
import com.postech.challenge_01.dtos.transfer.menu.NewMenuDTO;
import com.postech.challenge_01.infrastructure.entities.MenuEntity;
import com.postech.challenge_01.infrastructure.entities.RestaurantEntity;

import java.time.LocalDateTime;
import java.util.List;

public class MenuEntityMapper {
    public static MenuEntity toMenuEntity(NewMenuDTO dto) {
        var restaurant = new RestaurantEntity();
        restaurant.setId(dto.restaurantId());

        return new MenuEntity(
                null,
                restaurant,
                LocalDateTime.now(),
                List.of()
        );
    }

    public static MenuDTO toMenuDTO(MenuEntity entity) {
        return new MenuDTO(
                entity.getId(),
                entity.getRestaurant().getId(),
                entity.getLastModifiedDateTime()
        );
    }
}
