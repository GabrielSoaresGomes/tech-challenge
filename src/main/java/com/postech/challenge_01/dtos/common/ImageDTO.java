package com.postech.challenge_01.dtos.common;

import jakarta.annotation.Nonnull;

public record ImageDTO(
        @Nonnull byte[] content,
        @Nonnull String filename,
        @Nonnull String mimeType
) {
}
