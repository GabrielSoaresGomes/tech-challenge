package com.postech.challenge_01.exceptions;

public class UserTypeNameRequiredException extends RuntimeException {
    public UserTypeNameRequiredException() {
      super("O nome do tipo de usuário é obrigatório");
    }
}
