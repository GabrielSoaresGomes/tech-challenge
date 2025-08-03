package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.requests.address.FindAddressRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindAddressByIdAndUserIdUseCase implements UseCase<FindAddressRequestDTO, Address> {
    private final IAddressGateway gateway;

    @Override
    public Address execute(FindAddressRequestDTO request) {
        var addressId = request.addressId();

        return gateway.findById(addressId);
    }
}
