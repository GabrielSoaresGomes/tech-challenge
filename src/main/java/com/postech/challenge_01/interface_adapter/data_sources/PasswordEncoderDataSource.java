package com.postech.challenge_01.interface_adapter.data_sources;

public interface PasswordEncoderDataSource {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
