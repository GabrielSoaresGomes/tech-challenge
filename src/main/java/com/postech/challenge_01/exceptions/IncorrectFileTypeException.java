package com.postech.challenge_01.exceptions;

import jakarta.annotation.Nonnull;

import java.util.List;

public class IncorrectFileTypeException extends BusinessException {
    public IncorrectFileTypeException(@Nonnull String mimeType, @Nonnull List<String> allowedMimeTypes) {
        super(
                "O mime type %s não é permitido. Tipos permitidos: %s".formatted(
                        mimeType,
                        String.join(", ", allowedMimeTypes)
                )
        );
    }
}
