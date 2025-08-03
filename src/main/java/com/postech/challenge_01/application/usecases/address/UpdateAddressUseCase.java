package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.dtos.requests.address.AddressUpdateRequestDTO;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.application.mappers.AddressMapper;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateAddressUseCase implements UseCase<AddressUpdateRequestDTO, Address> {
    private final IAddressGateway gateway;
    private final List<Rule<Address>> rules;

    @Override
    public Address execute(AddressUpdateRequestDTO request) {
        var id = request.id();
        var data = request.data();

        var entity = AddressMapper.toAddress(id, data);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Atualizando endereço com ID {}: {}", id, entity);
        return this.gateway.update(entity, id);
    }
}
