package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.repositories.UserRepository;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public void execute(Long id) {
        log.info("Deletando usuário com ID: {}", id);
        var delete = this.userRepository.delete(id);

        if (delete == 0) {
            throw new UserNotFoundException("Usuário com ID " + id + " não foi encontrado");
        }
    }
}

