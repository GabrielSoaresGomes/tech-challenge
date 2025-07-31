package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.mappers.AddressMapper;
import com.postech.challenge_01.infrastructure.data_sources.repositories.address.AddressRepository;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllAddressesUseCase implements UseCase<Pageable, List<AddressResponseDTO>> {
    private final AddressRepository addressRepository;

    public List<AddressResponseDTO> execute(Pageable pageable) {
        log.info("Listando endereços");
        var entityList = this.addressRepository.findAll(pageable.getPageSize(), pageable.getOffset());
//        return AddressMapper.addressToAddressResponseDTOList(entityList);
        return null;
    }
}
