package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.repositories.address.AddressRepository;
import com.postech.challenge_01.repositories.user.UserRepository;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteUserUseCase implements UseCase<Long, Void> {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public Void execute(Long id) {
        log.info("Deletando endereços do usuário com ID: {}", id);
        this.addressRepository.deleteByUserId(id);

        log.info("Deletando usuário com ID: {}", id);
        var delete = this.userRepository.delete(id);

        if (delete == 0) {
            throw new UserNotFoundException(id);
        }

        return null;
    }
}

