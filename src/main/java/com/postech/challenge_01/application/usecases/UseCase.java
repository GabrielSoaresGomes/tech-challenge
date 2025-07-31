package com.postech.challenge_01.application.usecases;

public interface UseCase<I, O> {
    O execute(I request);
}
