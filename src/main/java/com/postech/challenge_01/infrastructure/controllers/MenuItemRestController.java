package com.postech.challenge_01.infrastructure.controllers;

import com.postech.challenge_01.dtos.requests.menu_item.MenuItemRequestDTO;
import com.postech.challenge_01.dtos.requests.menu_item.MenuItemUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.menu_item.MenuItemResponseDTO;
import com.postech.challenge_01.infrastructure.api.MenuItemRestApi;
import com.postech.challenge_01.interface_adapter.controllers.MenuItemController;
import com.postech.challenge_01.interface_adapter.presenters.ImagePresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-items")
public class MenuItemRestController implements MenuItemRestApi {
    private final MenuItemController controller;

    @Override
    @GetMapping
    public List<MenuItemResponseDTO> getMenuItem(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return this.controller.getMenuItemList(PageRequest.of(page, size));
    }

    @Override
    @GetMapping("/{id}")
    public MenuItemResponseDTO getMenuItemById(
            @PathVariable("id") Long id
    ) {
        return this.controller.getMenuItem(id);
    }

    @Override
    @GetMapping("/{id}/plate-photo")
    public ResponseEntity<byte[]> getPlatePhotoMenuItemById(
            @PathVariable("id") Long id
    ) {
        var platePhoto = this.controller.getMenuItemPlatePhoto(id);

        return ImagePresenter.present(platePhoto);
    }

    @Override
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

        return this.controller.saveMenuItem(request);
    }

    @Override
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

        return this.controller.updateMenuItem(request);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenuItem(
            @PathVariable("id") Long id
    ) {
        this.controller.deleteMenuItem(id);
    }
}
