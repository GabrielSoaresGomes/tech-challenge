package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.requests.address.FindAddressRequestDTO;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.usecases.UseCase;
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
        var userId = request.userId();

        return gateway.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Endereço não encontrado para o id " + addressId + " e usuário " + userId));
    }
}
