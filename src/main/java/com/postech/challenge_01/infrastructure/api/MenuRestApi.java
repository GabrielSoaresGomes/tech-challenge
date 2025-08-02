package com.postech.challenge_01.infrastructure.api;

import com.postech.challenge_01.dtos.requests.menu.MenuRequestDTO;
import com.postech.challenge_01.dtos.responses.menu.MenuResponseDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Menus", description = "Endpoints para gerenciamento de menus")
public interface MenuRestApi {
    @Operation(
            summary = "Busca por todos os menus",
            description = "Busca por todos os menus, informe o número de menus exibidos por página",
            tags = {"Menus"}
    )
    public List<MenuResponseDTO> getMenus(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );

    @Operation(
            summary = "Busca por somente um menu",
            description = "Busca menu pelo id, informe id do menu",
            tags = {"Menus"}
    )
    public MenuResponseDTO getMenuById(
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Busca os itens de um menu",
            description = "Busca os itens do menu pelo id, informe id do menu",
            tags = {"Menus"}
    )
    public List<MenuItemResponseDTO> getMenuItemsByMenuId(
            @PathVariable("id") long id,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );

    @Operation(
            summary = "Cria um menu",
            description = "Cria um menu, informe o ID do restaurante",
            tags = {"Menus"}
    )
    public MenuResponseDTO saveMenu(
            @RequestBody @Valid MenuRequestDTO menuRequestDTO
    );

    @Operation(
            summary = "Exclua um menu",
            description = "Exclua um menu, informe o id do menu",
            tags = {"Menus"}
    )
    public void deleteMenu(
            @PathVariable("id") Long id
    );
}
