package com.postech.challenge_01.application.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeRequestDTO;
import com.postech.challenge_01.application.mappers.UserTypeMapper;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveUserTypeUseCase implements UseCase<UserTypeRequestDTO, UserType> {
    private final IUserTypeGateway gateway;
    private final List<Rule<UserType>> rules;

    @Override
    public UserType execute(UserTypeRequestDTO userTypeRequestDTO) {
        var entity = UserTypeMapper.toUserType(userTypeRequestDTO);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Criando um novo tipo de usuário: {}", entity);
        var savedEntity = this.gateway.save(entity);

        log.info("Novo tipo de usuário criado: {}", savedEntity);
        return savedEntity;
    }
}
