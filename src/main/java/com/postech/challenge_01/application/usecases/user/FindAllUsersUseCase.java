package com.postech.challenge_01.application.usecases.user;

import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllUsersUseCase implements UseCase<Pageable, List<User>> {
    private final IUserGateway gateway;

    @Override
    public List<User> execute(Pageable pageable) {
        log.info("Listando usuários");
        return this.gateway.findAll(pageable);
    }
}
