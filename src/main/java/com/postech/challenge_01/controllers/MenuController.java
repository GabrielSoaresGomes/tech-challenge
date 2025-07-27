package com.postech.challenge_01.controllers;

import com.postech.challenge_01.dtos.requests.menu.MenuRequestDTO;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemsByMenuIdRequestDTO;
import com.postech.challenge_01.dtos.responses.menu.MenuResponseDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.usecases.menu.DeleteMenuUseCase;
import com.postech.challenge_01.usecases.menu.FindAllMenusUseCase;
import com.postech.challenge_01.usecases.menu.FindMenuByIdUseCase;
import com.postech.challenge_01.usecases.menu.SaveMenuUseCase;
import com.postech.challenge_01.usecases.menu_item.FindAllMenuItemsByMenuIdUseCase;
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
public class MenuController {
    private final FindAllMenusUseCase findAllMenusUseCase;
    private final FindAllMenuItemsByMenuIdUseCase findAllMenuItemsByMenuIdUseCase;
    private final FindMenuByIdUseCase findMenuByIdUseCase;
    private final SaveMenuUseCase saveMenuUseCase;
    private final DeleteMenuUseCase deleteMenuUseCase;

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
        return this.findAllMenusUseCase.execute(PageRequest.of(page, size));
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
        return this.findMenuByIdUseCase.execute(id);
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
        var request = new MenuItemsByMenuIdRequestDTO(id, page, size);
        return this.findAllMenuItemsByMenuIdUseCase.execute(request);
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
        return this.saveMenuUseCase.execute(menuRequestDTO);
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
        this.deleteMenuUseCase.execute(id);
    }
}
