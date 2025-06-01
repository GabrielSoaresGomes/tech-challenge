package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.dtos.requests.UserPasswordRequestDTO;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import com.postech.challenge_01.repositories.UserRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateUserPasswordUseCase implements UseCase<UserPasswordRequestDTO, Void> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Void execute(UserPasswordRequestDTO request) {
        var id = request.id();
        var passwordEncoded = passwordEncoder.encode(request.password());

        log.info("Atualizando a senha do usuário com ID {}", id);
        var hasUpdated = userRepository.updatePassword(id, passwordEncoded);
        if(!hasUpdated) {
            throw new UserNotFoundException(id);
        }

        return null;
    }
}
