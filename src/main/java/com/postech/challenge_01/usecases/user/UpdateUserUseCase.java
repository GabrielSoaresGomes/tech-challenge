package com.postech.challenge_01.usecases.user;

import com.postech.challenge_01.dtos.requests.user.UserUpdateRequestDTO;
import com.postech.challenge_01.dtos.responses.UserResponseDTO;
import com.postech.challenge_01.domains.User;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import com.postech.challenge_01.mappers.UserMapper;
import com.postech.challenge_01.repositories.user.UserRepository;
import com.postech.challenge_01.usecases.UseCase;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateUserUseCase implements UseCase<UserUpdateRequestDTO, UserResponseDTO> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final List<Rule<User>> rules;

    @Override
    public UserResponseDTO execute(UserUpdateRequestDTO request) {
        var id = request.id();
        var data = request.data();

        var passwordEncoded = passwordEncoder.encode(data.password());
        var entity = UserMapper.userRequestDTOToUser(id, data, passwordEncoded);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Atualizando usuário com ID {}: {}", id, entity);
        var updatedEntity = this.userRepository.update(entity, id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserMapper.userToUserResponseDTO(updatedEntity);
    }
}

