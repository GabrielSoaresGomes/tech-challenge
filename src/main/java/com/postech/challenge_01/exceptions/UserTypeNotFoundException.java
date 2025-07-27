package com.postech.challenge_01.exceptions;

public class UserTypeNotFoundException extends ResourceNotFoundException {
    public UserTypeNotFoundException(Long id) {
        super("Tipo de usuário com ID " + id + " não foi encontrado");
    }
}

