package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAllAddressesUseCase implements UseCase<Pageable, List<Address>> {
    private final IAddressGateway gateway;

    @Override
    public List<Address> execute(Pageable pageable) {
        log.info("Listando endereços");
        return gateway.findAll(pageable);
    }
}
