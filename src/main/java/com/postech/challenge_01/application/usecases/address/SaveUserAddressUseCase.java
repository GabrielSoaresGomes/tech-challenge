package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.gateways.IUserAddressGateway;
import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.mappers.AddressMapper;
import com.postech.challenge_01.application.mappers.UserAddressMapper;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.requests.address.NewAddressWithUserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SaveUserAddressUseCase implements UseCase<NewAddressWithUserRequestDTO, Address>{

    private final IAddressGateway addressGateway;
    private final IUserGateway userGateway;
    private final IUserAddressGateway userAddressGateway;
    private final List<Rule<Address>> rules;

    @Override
    public Address execute(NewAddressWithUserRequestDTO addressWithUserRequestDTO) {

        var user = userGateway.findById(addressWithUserRequestDTO.userId());

        Address entity = AddressMapper.toAddress(addressWithUserRequestDTO);
        rules.forEach(rule -> rule.execute(entity));

        Address savedAddress = addressGateway.save(entity);
        var userAddress = UserAddressMapper.toUserAddress(user.getId(), savedAddress.getId());
        userAddressGateway.save(userAddress);

        return savedAddress;
    }
}
