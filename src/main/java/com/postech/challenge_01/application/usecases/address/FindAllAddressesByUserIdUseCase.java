package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.dtos.requests.address.FindAllAddressesByUserIdRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllAddressesByUserIdUseCase implements UseCase<FindAllAddressesByUserIdRequestDTO, List<Address>> {
    private final IAddressGateway gateway;

    @Transactional
    @Override
    public List<Address> execute(FindAllAddressesByUserIdRequestDTO request) {
        log.info("Listando endereços do user {}", request.userId());
        var list = this.gateway.findAllByUserId(request.userId(), request.pageable().getPageSize(), request.pageable().getOffset());

        log.info("Endereços do user {}, endereços: {}", request.userId(), list);
        return list;
    }
}
