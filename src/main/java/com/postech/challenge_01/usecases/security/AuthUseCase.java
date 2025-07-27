package com.postech.challenge_01.usecases.security;

import com.postech.challenge_01.dtos.security.AccountCredentialsDTO;
import com.postech.challenge_01.dtos.security.TokenDTO;
import com.postech.challenge_01.domains.User;
import com.postech.challenge_01.repositories.user.UserRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Component
public class AuthUseCase implements UseCase<AccountCredentialsDTO, TokenDTO> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${security.token.expire-length:3600000}")
    private final long validityInMilliseconds = 3600000; //1h

    @Override
    public TokenDTO execute(AccountCredentialsDTO accountCredentialsDTO) {
        var login = accountCredentialsDTO.login();
        var password = accountCredentialsDTO.password();
        var user = findUserByLogin(login);

        validateCredentials(password, user);

        return createAccessToken(user.getLogin());
    }

    private User findUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Login "+ login +" não encontrado!"));
    }

    private TokenDTO createAccessToken(String login) {
        var now = LocalDateTime.now();
        var validity = now.plus(this.validityInMilliseconds, ChronoUnit.MILLIS);

        return new TokenDTO(login, true, now, validity);
    }

    private void validateCredentials(String password, User user) {
        var passwordIsCorrect = passwordEncoder.matches(password, user.getPassword());
        if (!passwordIsCorrect) {
            throw new BadCredentialsException("Credenciais inválidas");
        }
    }
}
