package com.postech.challenge_01.exceptions;

public class AddressNotFoundException extends ResourceNotFoundException {
    public AddressNotFoundException(Long id) {
        super("Endereço com ID " + id + " não foi encontrado");
    }
}

