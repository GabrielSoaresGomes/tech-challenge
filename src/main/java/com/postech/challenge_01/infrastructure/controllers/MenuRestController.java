package com.postech.challenge_01.infrastructure.controllers;

import com.postech.challenge_01.dtos.requests.menu.MenuRequestDTO;
import com.postech.challenge_01.dtos.responses.menu.MenuResponseDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.infrastructure.api.MenuRestApi;
import com.postech.challenge_01.interface_adapter.controllers.MenuController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
public class MenuRestController implements MenuRestApi {
    private final MenuController controller;

    @Override
    @GetMapping
    public List<MenuResponseDTO> getMenus(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var request = PageRequest.of(page, size);

        return this.controller.getMenuList(request);
    }

    @Override
    @GetMapping("/{id}")
    public MenuResponseDTO getMenuById(
            @PathVariable("id") Long id
    ) {
        return this.controller.getMenu(id);
    }

    @Override
    @GetMapping("/{id}/items")
    public List<MenuItemResponseDTO> getMenuItemsByMenuId(
            @PathVariable("id") long id,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var pageable = PageRequest.of(page, size);

        return this.controller.getMenuItemListByMenuId(id, pageable);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuResponseDTO saveMenu(
            @RequestBody @Valid MenuRequestDTO menuRequestDTO
    ) {
        return this.controller.saveMenu(menuRequestDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenu(
            @PathVariable("id") Long id
    ) {
        this.controller.deleteMenu(id);
    }
}
