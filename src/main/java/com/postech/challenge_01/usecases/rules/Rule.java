package com.postech.challenge_01.usecases.rules;

public interface Rule<E> {
    void execute(E entity);
}
