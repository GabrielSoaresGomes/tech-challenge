package com.postech.challenge_01.exceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Long id) {
        super("Endereço com ID " + id + " não foi encontrado");
    }
}

