package com.postech.challenge_01.exceptions;

public class RestaurantNotFoundException extends ResourceNotFoundException {
    public RestaurantNotFoundException(Long id) {
        super("Restaurante com ID %d não foi encontrado".formatted(id));
    }
}
