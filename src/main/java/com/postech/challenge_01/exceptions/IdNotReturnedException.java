package com.postech.challenge_01.exceptions;

public class IdNotReturnedException extends RuntimeException {
    public IdNotReturnedException() {
        super("ID é requirido mas não foi encontrado");
    }
}

