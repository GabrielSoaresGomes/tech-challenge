package com.postech.challenge_01.exceptions;

public class MenuNotFoundException extends ResourceNotFoundException {
    public MenuNotFoundException(Long id) {
        super("Menu com ID %d não foi encontrado".formatted(id));
    }
}
