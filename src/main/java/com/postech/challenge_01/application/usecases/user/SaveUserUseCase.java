package com.postech.challenge_01.application.usecases.user;

import com.postech.challenge_01.application.gateways.IPasswordEncoderGateway;
import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.dtos.requests.user.UserRequestDTO;
import com.postech.challenge_01.application.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveUserUseCase implements UseCase<UserRequestDTO, User> {
    private final IUserGateway gateway;
    private final IPasswordEncoderGateway passwordEncoderGateway;
    private final List<Rule<User>> rules;

    @Override
    public User execute(UserRequestDTO userRequestDTO) {
        var passwordEncoded = passwordEncoderGateway.encode(userRequestDTO.password());
        var entity = UserMapper.toUser(userRequestDTO, passwordEncoded);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Criando novo usuário: {}", entity);
        var savedEntity = this.gateway.save(entity);

        log.info("Usuário criado: {}", savedEntity);
        return savedEntity;
    }
}
