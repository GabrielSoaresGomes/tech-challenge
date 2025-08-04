package com.postech.challenge_01.infrastructure.api;

import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "MenuItems", description = "Endpoints para gerenciamento de itens do menu")
public interface MenuItemRestApi {
    @Operation(
            summary = "Busca por todos os itens do menu",
            description = "Busca por todos os itens do menu, informe o número de itens do menu exibidos por página",
            tags = {"MenuItems"}
    )
    public List<MenuItemResponseDTO> getMenuItem(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );

    @Operation(
            summary = "Busca por somente um item do menu",
            description = "Busca item do menu pelo id, informe id do item do menu",
            tags = {"MenuItems"}
    )
    public MenuItemResponseDTO getMenuItemById(
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Busca pela foto do prato de um item do menu",
            description = "Busca item do menu pelo id, informe id do item do menu",
            tags = {"MenuItems"}
    )
    public ResponseEntity<byte[]> getPlatePhotoMenuItemById(
            @PathVariable("id") Long id
    );

    @Operation(
            summary = "Cria um item do menu",
            description = "Cria um item do menu, informe ID do menu, nome, descrição, condicional para somente comer no local e foto do prato",
            tags = {"MenuItems"}
    )
    public MenuItemResponseDTO saveMenuItem(
            @RequestParam() Long menuId,
            @RequestParam() String name,
            @RequestParam(required = false) String description,
            @RequestParam() BigDecimal price,
            @RequestParam() Boolean dineInOnly,
            @RequestParam() MultipartFile platePhoto
    );

    @Operation(
            summary = "Atualize um item do menu",
            description = "Atualize um item do menu, informe o campo que deseja alterar",
            tags = {"MenuItems"}
    )
    public MenuItemResponseDTO updateMenuItem(
            @PathVariable(value = "id") Long id,
            @RequestParam() String name,
            @RequestParam(required = false) String description,
            @RequestParam() BigDecimal price,
            @RequestParam() Boolean dineInOnly,
            @RequestParam() MultipartFile platePhoto
    );

    @Operation(
            summary = "Exclua um item do menu",
            description = "Exclua um item do menu, informe o id do item do menu",
            tags = {"MenuItems"}
    )
    public void deleteMenuItem(
            @PathVariable("id") Long id
    );
}
