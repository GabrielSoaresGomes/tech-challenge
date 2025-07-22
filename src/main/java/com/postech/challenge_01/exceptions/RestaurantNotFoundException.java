package com.postech.challenge_01.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long id) {super("Restaurante com ID " + id + " não foi encontrado");}
}
