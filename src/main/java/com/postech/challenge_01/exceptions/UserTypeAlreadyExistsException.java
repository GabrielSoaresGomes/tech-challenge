package com.postech.challenge_01.exceptions;

public class UserTypeAlreadyExistsException extends BusinessException {
    public UserTypeAlreadyExistsException(String name) {
      super("Tipo de usuário já existente com nome: " + name);
    }
}
