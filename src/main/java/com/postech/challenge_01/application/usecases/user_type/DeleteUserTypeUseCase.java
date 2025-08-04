package com.postech.challenge_01.application.usecases.user_type;

import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteUserTypeUseCase implements UseCase<Long, Void> {
    private final IUserTypeGateway gateway;
    private final List<Rule<UserType>> rules;

    @Override
    public Void execute(Long id) {
        var entity = this.gateway.findById(id);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Deletando tipo de usuário com ID: {}", id);
        this.gateway.delete(id);

        return null;
    }
}
