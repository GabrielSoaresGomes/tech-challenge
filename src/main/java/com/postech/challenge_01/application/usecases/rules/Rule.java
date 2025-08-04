package com.postech.challenge_01.application.usecases.rules;

public interface Rule<E> {
    void execute(E entity);
}
