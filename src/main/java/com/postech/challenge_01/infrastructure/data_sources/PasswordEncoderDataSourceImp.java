package com.postech.challenge_01.infrastructure.data_sources;

import com.postech.challenge_01.interface_adapter.data_sources.PasswordEncoderDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderDataSourceImp implements PasswordEncoderDataSource {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
