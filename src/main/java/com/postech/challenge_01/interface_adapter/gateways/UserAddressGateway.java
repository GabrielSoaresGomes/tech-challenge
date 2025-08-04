package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.application.gateways.IUserAddressGateway;
import com.postech.challenge_01.application.mappers.UserAddressMapper;
import com.postech.challenge_01.domain.UserAddress;
import com.postech.challenge_01.infrastructure.data_sources.repositories.user_address.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAddressGateway implements IUserAddressGateway {

    private final UserAddressRepository repository;

    @Override
    public UserAddress save(UserAddress entity) {
        var newAddressDTO = UserAddressMapper.toNewUserAddressDTO(entity);
        var savedAddressDTO = repository.save(newAddressDTO);
        return UserAddressMapper.toUserAddress(savedAddressDTO);
    }
}
