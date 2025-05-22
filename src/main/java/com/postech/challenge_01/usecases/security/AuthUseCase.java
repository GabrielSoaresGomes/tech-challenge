package com.postech.challenge_01.usecases.security;

import com.postech.challenge_01.dtos.security.AccountCredentialsDTO;
import com.postech.challenge_01.dtos.security.TokenDTO;
import com.postech.challenge_01.entities.User;
import com.postech.challenge_01.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthUseCase {

    @Value("${security.token.expire-length:3600000}")
    private final long validyInMilliseconds = 3600000; //1h

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        Date now = new Date();
        Date validity = new Date(now.getTime() + validyInMilliseconds);

        return new TokenDTO(login, true, now, validity);
    }

    private void validateCredentials(String login, User user) {
        var passwordIsCorrect = passwordEncoder.matches(login, user.getPassword());
        if (!passwordIsCorrect) {
            throw new BadCredentialsException("Credenciais inválidas");
        }
    }
}
