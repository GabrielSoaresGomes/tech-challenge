package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.mappers.AddressMapper;
import com.postech.challenge_01.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllAddressesByUserIdUseCase {
    private final AddressRepository addressRepository;

    public List<AddressResponseDTO> execute(Long userId, int page, int size) {
        int offset = (page - 1) * size;

        log.info("Listando endereços");
        var entityList = this.addressRepository.findAllByUserId(userId, size, offset);
        return AddressMapper.addressToAddressResponseDTOList(entityList);
    }
}
