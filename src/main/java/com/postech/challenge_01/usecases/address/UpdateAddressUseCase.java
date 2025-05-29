package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.dtos.requests.AddressRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.mappers.AddressMapper;
import com.postech.challenge_01.repositories.AddressRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateAddressUseCase {
    private final AddressRepository addressRepository;
    private final List<Rule<Address>> rules;

    public AddressResponseDTO execute(AddressRequestDTO addressRequestDTO, Long id) {
        Address entity = AddressMapper.addressRequestDTOToAddress(id, addressRequestDTO);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Atualizando endereço com ID {}: {}", id, entity);
        Address updatedEntity = this.addressRepository.update(entity, id);
        return AddressMapper.addressToAddressResponseDTO(updatedEntity);
    }
}
