package com.postech.challenge_01.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long id) {
        super("Restaurante com ID %d não foi encontrado".formatted(id));
    }
}
