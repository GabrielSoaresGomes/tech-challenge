package com.postech.challenge_01.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super("O e-mail '" + email + "' é inválido");
    }
}