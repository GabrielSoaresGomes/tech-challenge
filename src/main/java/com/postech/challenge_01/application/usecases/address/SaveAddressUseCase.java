package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.application.mappers.AddressMapper;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveAddressUseCase implements UseCase<AddressRequestDTO, Address> {
    private final IAddressGateway gateway;
    private final List<Rule<Address>> rules;

    @Override
    public Address execute(AddressRequestDTO addressRequestDTO) {
        var entity = AddressMapper.toAddress(addressRequestDTO);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Criando novo endereço: {}", entity);
        var savedEntity = this.gateway.save(entity);

        log.info("Endereço criado: {}", savedEntity);
        return savedEntity;
    }
}
