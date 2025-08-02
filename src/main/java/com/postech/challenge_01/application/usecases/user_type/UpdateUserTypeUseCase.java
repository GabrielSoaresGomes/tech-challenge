package com.postech.challenge_01.application.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.domain.UserType;
import com.postech.challenge_01.dtos.requests.user_type.UserTypeUpdateRequestDTO;
import com.postech.challenge_01.mappers.user_type.UserTypeMapper;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateUserTypeUseCase implements UseCase<UserTypeUpdateRequestDTO, UserType> {
    private final IUserTypeGateway gateway;
    private final List<Rule<UserType>> rules;

    @Override
    public UserType execute(UserTypeUpdateRequestDTO request) {
        var id = request.id();
        var data = request.data();

        var entity = UserTypeMapper.toUserType(id, data);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Atualizando tipo do usuário com ID {}: {}", id, entity);
        return this.gateway.update(entity, id);
    }
}
