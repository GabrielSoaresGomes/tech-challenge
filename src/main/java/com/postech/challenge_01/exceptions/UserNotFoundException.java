package com.postech.challenge_01.exceptions;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(Long id) {
        super("Usuário com ID " + id + " não foi encontrado");
    }
}

