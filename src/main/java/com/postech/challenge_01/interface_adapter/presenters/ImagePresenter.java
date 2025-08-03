package com.postech.challenge_01.interface_adapter.presenters;

import com.postech.challenge_01.domain.MenuItem;
import com.postech.challenge_01.dtos.common.ImageDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ImagePresenter {
    public static ResponseEntity<byte[]> present(ImageDTO image) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", image.mimeType());
        headers.set("Content-Disposition", "inline; filename=\"" + image.filename() + "\"");

        return new ResponseEntity<>(image.content(), headers, HttpStatus.OK);
    }

    public static ImageDTO menuItemToImageDTO(MenuItem menuItem) {
        return new ImageDTO(
                menuItem.getPlatePhotoContent(),
                menuItem.getPlatePhotoOriginalFilename(),
                menuItem.getPlatePhotoMimeType()
        );
    }
}
