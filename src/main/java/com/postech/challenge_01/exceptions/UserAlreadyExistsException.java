package com.postech.challenge_01.exceptions;

public class UserAlreadyExistsException extends BusinessException {
    public UserAlreadyExistsException(String login) {
        super("Usuário com login '" + login + "' já existe");
    }
}
