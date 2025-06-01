package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.mappers.UserMapper;
import com.postech.challenge_01.repositories.UserRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindUserByIdUseCase implements UseCase<Long, UserResponseDTO> {
    private final UserRepository userRepository;

    @Override
    public UserResponseDTO execute(Long id) {
        log.info("Buscando usuário com ID: {}", id);
        var entity = this.userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o id " + id));

        log.info("Usuário encontrado: {}", entity);
        return UserMapper.userToUserResponseDTO(entity);
    }
}
