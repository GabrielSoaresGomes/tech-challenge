package com.postech.challenge_01.infrastructure.controllers;

import com.postech.challenge_01.dtos.requests.menu.MenuRequestDTO;
import com.postech.challenge_01.dtos.responses.menu.MenuResponseDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.interface_adapter.controllers.MenuController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Menus", description = "Endpoints para gerenciamento de menus")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
public class MenuRestController {
    private final MenuController controller;

    @Operation(
            summary = "Busca por todos os menus",
            description = "Busca por todos os menus, informe o número de menus exibidos por página",
            tags = {"Menus"}
    )
    @GetMapping
    public List<MenuResponseDTO> getMenus(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var request = PageRequest.of(page, size);

        return this.controller.getMenuList(request);
    }

    @Operation(
            summary = "Busca por somente um menu",
            description = "Busca menu pelo id, informe id do menu",
            tags = {"Menus"}
    )
    @GetMapping("/{id}")
    public MenuResponseDTO getMenuById(
            @PathVariable("id") Long id
    ) {
        return this.controller.getMenu(id);
    }

    @Operation(
            summary = "Busca os itens de um menu",
            description = "Busca os itens do menu pelo id, informe id do menu",
            tags = {"Menus"}
    )
    @GetMapping("/{id}/items")
    public List<MenuItemResponseDTO> getMenuItemsByMenuId(
            @PathVariable("id") long id,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var pageable = PageRequest.of(page, size);

        return this.controller.getMenuItemListByMenuId(id, pageable);
    }

    @Operation(
            summary = "Cria um menu",
            description = "Cria um menu, informe o ID do restaurante",
            tags = {"Menus"}
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuResponseDTO saveMenu(
            @RequestBody @Valid MenuRequestDTO menuRequestDTO
    ) {
        return this.controller.saveMenu(menuRequestDTO);
    }

    @Operation(
            summary = "Exclua um menu",
            description = "Exclua um menu, informe o id do menu",
            tags = {"Menus"}
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenu(
            @PathVariable("id") Long id
    ) {
        this.controller.deleteMenu(id);
    }
}
