package com.postech.challenge_01.application.usecases.user;

import com.postech.challenge_01.application.gateways.IPasswordEncoderGateway;
import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.User;
import com.postech.challenge_01.dtos.requests.user.UserUpdateRequestDTO;
import com.postech.challenge_01.interface_adapter.gateways.PasswordEncoderGateway;
import com.postech.challenge_01.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateUserUseCase implements UseCase<UserUpdateRequestDTO, User> {
    private final IUserGateway gateway;
    private final IPasswordEncoderGateway passwordEncoderGateway;
    private final List<Rule<User>> rules;

    @Override
    public User execute(UserUpdateRequestDTO request) {
        var id = request.id();
        var data = request.data();

        var passwordEncoded = passwordEncoderGateway.encode(data.password());
        var entity = UserMapper.toUser(id, data, passwordEncoded);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Atualizando usuário com ID {}: {}", id, entity);
        return this.gateway.update(entity, id);
    }
}

