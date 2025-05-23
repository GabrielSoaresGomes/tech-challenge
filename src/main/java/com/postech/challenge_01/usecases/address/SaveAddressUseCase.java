package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.dtos.requests.AddressRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.entities.Address;
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
public class SaveAddressUseCase {
    private final AddressRepository addressRepository;
    private final List<Rule<Address>> rules;

    public AddressResponseDTO execute(AddressRequestDTO addressRequestDTO) {
        Address entity = AddressMapper.addressRequestDTOToAddress(addressRequestDTO);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Criando novo endereço: {}", entity);
        Address savedEntity = this.addressRepository.save(entity);

        log.info("Endereço criado: {}", savedEntity);
        return AddressMapper.addressToAddressResponseDTO(savedEntity);
    }
}
