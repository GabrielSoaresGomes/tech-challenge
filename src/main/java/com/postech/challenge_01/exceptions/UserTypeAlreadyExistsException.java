package com.postech.challenge_01.exceptions;

public class UserTypeAlreadyExistsException extends RuntimeException {
    public UserTypeAlreadyExistsException(String name) {
      super("Tipo de usuário já existente com nome: " + name);
    }
}
