package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.dtos.requests.address.FindAllAddressesByUserIdRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.mappers.AddressMapper;
import com.postech.challenge_01.repositories.AddressRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllAddressesByUserIdUseCase implements UseCase<FindAllAddressesByUserIdRequestDTO, List<AddressResponseDTO>> {
    private final AddressRepository addressRepository;

    @Override
    public List<AddressResponseDTO> execute(FindAllAddressesByUserIdRequestDTO request) {
        var pageable = request.pageable();
        var userId = request.userId();

        log.info("Listando endereços");
        var entityList = this.addressRepository.findAllByUserId(userId, pageable.getPageSize(), pageable.getOffset());
        return AddressMapper.addressToAddressResponseDTOList(entityList);
    }
}
