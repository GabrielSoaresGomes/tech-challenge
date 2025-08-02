package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.application.mappers.AddressMapper;
import com.postech.challenge_01.infrastructure.data_sources.repositories.address.AddressRepository;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveAddressUseCase implements UseCase<AddressRequestDTO, AddressResponseDTO> {
    private final AddressRepository addressRepository;
    private final List<Rule<Address>> rules;

    @Override
    public AddressResponseDTO execute(AddressRequestDTO addressRequestDTO) {
        Address entity = AddressMapper.addressRequestDTOToAddress(addressRequestDTO);

        rules.forEach(rule -> rule.execute(entity));

        log.info("Criando novo endereço: {}", entity);
//        Address savedEntity = this.addressRepository.save(entity);
//
//        log.info("Endereço criado: {}", savedEntity);
//        return AddressMapper.addressToAddressResponseDTO(savedEntity);
        return null;
    }
}
