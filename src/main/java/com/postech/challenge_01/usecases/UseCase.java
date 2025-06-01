package com.postech.challenge_01.usecases;

public interface UseCase<I, O> {
    O execute(I request);
}
