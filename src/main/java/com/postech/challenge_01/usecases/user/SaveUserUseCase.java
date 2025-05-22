package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.entities.User;
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
public class SaveUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final List<Rule<User>> rules;

    public UserResponseDTO execute(UserRequestDTO userRequestDTO) {
        var passwordEncoded = passwordEncoder.encode(userRequestDTO.password());
        var entity = UserMapper.userRequestDTOToUser(userRequestDTO, passwordEncoded);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Criando novo usuário: {}", entity);
        var savedEntity = this.userRepository.save(entity);

        log.info("Usuário criado: {}", savedEntity);
        return UserMapper.userToUserResponseDTO(savedEntity);
    }
}
