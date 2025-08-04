package com.postech.challenge_01.exceptions;

public class UserTypeIsUsedException extends BusinessException {
    public UserTypeIsUsedException(String name) {
        super("Erro ao apagar tipo '" + name + "', existe usuários vinculados a ele.");
    }
}
