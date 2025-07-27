package com.postech.challenge_01.dtos.requests.menu_item;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record MenuItemRequestDTO(
        @NotNull(message = "O ID do menu não pode ser nulo")
        Long menuId,
        @NotNull(message = "O nome não pode ser nulo")
        String name,
        @NotNull(message = "A descrição não pode ser nula")
        String description,
        @NotNull(message = "O preço não pode ser nulo")
        BigDecimal price,
        @NotNull(message = "A opção de consumo apenas no local não pode ser nulo")
        Boolean dineInOnly,
        @NotNull(message = "A foto do prato não pode ser nula")
        MultipartFile platePhoto
) {
}
