package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.domains.User;
import com.postech.challenge_01.mappers.UserMapper;
import com.postech.challenge_01.repositories.UserRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final List<Rule<User>> rules;

    public UserResponseDTO execute(UserRequestDTO userRequestDTO, Long id) {
        var passwordEncoded = passwordEncoder.encode(userRequestDTO.password());
        var entity = UserMapper.userRequestDTOToUser(id, userRequestDTO, passwordEncoded);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Atualizando usuário com ID {}: {}", id, entity);
        var updatedEntity = this.userRepository.update(entity, id);
        return UserMapper.userToUserResponseDTO(updatedEntity);
    }
}

