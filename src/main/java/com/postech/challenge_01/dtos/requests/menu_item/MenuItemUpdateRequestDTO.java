package com.postech.challenge_01.dtos.requests.menu_item;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record MenuItemUpdateRequestDTO(
        @NotNull(message = "O ID não pode ser nulo")
        Long id,
        @NotNull(message = "O nome não pode ser nulo")
        String name,
        String description,
        @NotNull(message = "O preço não pode ser nulo")
        BigDecimal price,
        @NotNull(message = "A opção de consumo apenas no local não pode ser nulo")
        Boolean dineInOnly,
        @NotNull(message = "A foto do prato não pode ser nula")
        MultipartFile platePhoto
) {
}
