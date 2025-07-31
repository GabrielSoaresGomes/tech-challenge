package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.mappers.AddressMapper;
import com.postech.challenge_01.infrastructure.data_sources.repositories.address.AddressRepository;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAddressByIdUseCase implements UseCase<Long, AddressResponseDTO> {
    private final AddressRepository addressRepository;

    @Override
    public AddressResponseDTO execute(Long id) {
        log.info("Buscando endereço com ID: {}", id);
        var entity = this.addressRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado para o id " + id));

        log.info("Endereço encontrado: {}", entity);
//        return AddressMapper.addressToAddressResponseDTO(entity);
        return null;
    }
}
