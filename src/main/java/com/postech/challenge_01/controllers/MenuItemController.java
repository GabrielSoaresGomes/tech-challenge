package com.postech.challenge_01.controllers;

import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.presenters.ImagePresenter;
import com.postech.challenge_01.usecases.menu_item.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "MenuItems", description = "Endpoints para gerenciamento de itens do menu")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-items")
public class MenuItemController {
    private final SaveMenuItemUseCase saveMenuItemUseCase;
    private final FindAllMenuItemsUseCase findAllMenuItemsUseCase;
    private final FindMenuItemByIdUseCase findMenuItemByIdUseCase;
    private final FindPlatePhotoMenuItemByIdUseCase findPlatePhotoMenuItemByIdUseCase;
    private final UpdateMenuItemUseCase updateMenuItemUseCase;
    private final DeleteMenuItemUseCase deleteMenuItemUseCase;

    @Operation(
            summary = "Busca por todos os itens do menu",
            description = "Busca por todos os itens do menu, informe o número de itens do menu exibidos por página",
            tags = {"MenuItems"}
    )
    @GetMapping
    public List<MenuItemResponseDTO> getMenuItem(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return this.findAllMenuItemsUseCase.execute(PageRequest.of(page, size));
    }

    @Operation(
            summary = "Busca por somente um item do menu",
            description = "Busca item do menu pelo id, informe id do item do menu",
            tags = {"MenuItems"}
    )
    @GetMapping("/{id}")
    public MenuItemResponseDTO getMenuItemById(
            @PathVariable("id") Long id
    ) {
        return this.findMenuItemByIdUseCase.execute(id);
    }

    @Operation(
            summary = "Busca pela foto do prato de um item do menu",
            description = "Busca item do menu pelo id, informe id do item do menu",
            tags = {"MenuItems"}
    )
    @GetMapping("/{id}/plate-photo")
    public ResponseEntity<byte[]> getPlatePhotoMenuItemById(
            @PathVariable("id") Long id
    ) {
        var platePhoto = this.findPlatePhotoMenuItemByIdUseCase.execute(id);

        return ImagePresenter.present(platePhoto);
    }

    @Operation(
            summary = "Cria um item do menu",
            description = "Cria um item do menu, informe ID do menu, nome, descrição, condicional para somente comer no local e foto do prato",
            tags = {"MenuItems"}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MenuItemResponseDTO saveMenuItem(
            @RequestParam() Long menuId,
            @RequestParam() String name,
            @RequestParam(required = false) String description,
            @RequestParam() BigDecimal price,
            @RequestParam() Boolean dineInOnly,
            @RequestParam() MultipartFile platePhoto
    ) {
        var request = new MenuItemRequestDTO(menuId, name, description, price, dineInOnly, platePhoto);

        return this.saveMenuItemUseCase.execute(request);
    }

    @Operation(
            summary = "Atualize um item do menu",
            description = "Atualize um item do menu, informe o campo que deseja alterar",
            tags = {"MenuItems"}
    )
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MenuItemResponseDTO updateMenuItem(
            @PathVariable(value = "id") Long id,
            @RequestParam() String name,
            @RequestParam(required = false) String description,
            @RequestParam() BigDecimal price,
            @RequestParam() Boolean dineInOnly,
            @RequestParam() MultipartFile platePhoto
    ) {
        var request = new MenuItemUpdateRequestDTO(id, name, description, price, dineInOnly, platePhoto);

        return this.updateMenuItemUseCase.execute(request);
    }

    @Operation(
            summary = "Exclua um item do menu",
            description = "Exclua um item do menu, informe o id do item do menu",
            tags = {"MenuItems"}
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenuItem(
            @PathVariable("id") Long id
    ) {
        this.deleteMenuItemUseCase.execute(id);
    }
}
