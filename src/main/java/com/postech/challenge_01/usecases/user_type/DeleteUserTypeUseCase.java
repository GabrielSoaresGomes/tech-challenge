package com.postech.challenge_01.usecases.user_type;

import com.postech.challenge_01.exceptions.UserTypeNotFoundException;
import com.postech.challenge_01.repositories.user_type.UserTypeRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteUserTypeUseCase implements UseCase<Long, Void> {
    private final UserTypeRepository userTypeRepository;

    @Override
    public Void execute(Long id) {
        log.info("Deletando tipo de usuário com ID: {}", id);
        var delete = this.userTypeRepository.delete(id);

        if (delete == 0) {
            throw new UserTypeNotFoundException(id);
        }

        return null;
    }
}
