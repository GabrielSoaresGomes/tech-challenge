package com.postech.challenge_01.exceptions;

public class UserIsNotOwnerException extends ResourceNotFoundException {
    public UserIsNotOwnerException(Long id) {
        super("Usuário de ID " + id + " não é considerado Dono");
    }
}

