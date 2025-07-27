package com.postech.challenge_01.presenters;

import com.postech.challenge_01.dtos.common.ImageDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ImagePresenter {
    public static ResponseEntity<byte[]> present(ImageDto image) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", image.mimeType());
        headers.set("Content-Disposition", "inline; filename=\"" + image.filename() + "\"");

        return new ResponseEntity<>(image.content(), headers, HttpStatus.OK);
    }
}
