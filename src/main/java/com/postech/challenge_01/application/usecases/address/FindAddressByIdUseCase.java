package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAddressByIdUseCase implements UseCase<Long, Address> {
    private final IAddressGateway gateway;

    @Override
    public Address execute(Long id) {
        log.info("Buscando endereço com ID: {}", id);
        var entity = this.gateway.findById(id);

        log.info("Endereço encontrado: {}", entity);
        return entity;
    }
}
