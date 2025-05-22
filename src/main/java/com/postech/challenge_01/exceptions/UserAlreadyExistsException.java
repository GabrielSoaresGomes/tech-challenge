package com.postech.challenge_01.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String login) {
        super("Usuário com login '" + login + "' já existe");
    }
}
