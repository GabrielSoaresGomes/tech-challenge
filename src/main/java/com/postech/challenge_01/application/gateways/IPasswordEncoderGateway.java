package com.postech.challenge_01.application.gateways;

public interface IPasswordEncoderGateway {
    String encode(CharSequence rawPassword);
    void matches(CharSequence rawPassword, String encodedPassword);
}