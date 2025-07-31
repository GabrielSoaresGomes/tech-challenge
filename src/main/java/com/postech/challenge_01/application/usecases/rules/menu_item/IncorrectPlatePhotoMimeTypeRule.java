package com.postech.challenge_01.application.usecases.rules.menu_item;

import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.exceptions.IncorrectFileTypeException;
import com.postech.challenge_01.application.usecases.rules.Rule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IncorrectPlatePhotoMimeTypeRule implements Rule<MenuItem> {
    private static final List<String> ALLOWED_MIME_TYPES = List.of(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/webp",
            "image/bmp",
            "image/svg+xml"
    );

    @Override
    public void execute(MenuItem menuItem) {
        var platePhotoMimeType = menuItem.getPlatePhotoMimeType();

        if (!ALLOWED_MIME_TYPES.contains(platePhotoMimeType)) {
            throw new IncorrectFileTypeException(platePhotoMimeType, ALLOWED_MIME_TYPES);
        }
    }
}
