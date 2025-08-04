package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.application.gateways.IPasswordEncoderGateway;
import com.postech.challenge_01.interface_adapter.data_sources.PasswordEncoderDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderGateway implements IPasswordEncoderGateway {
    private final PasswordEncoderDataSource passwordEncoderDataSource;

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoderDataSource.encode(rawPassword);
    }

    @Override
    public void matches(CharSequence rawPassword, String encodedPassword) {
        var passwordIsCorrect = passwordEncoderDataSource.matches(rawPassword, encodedPassword);
        if (!passwordIsCorrect) {
            throw new BadCredentialsException("Credenciais inválidas");
        }
    }
}
