package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.domains.UserAddress;
import com.postech.challenge_01.dtos.requests.address.AddressWithUserRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.entities.AddressEntity;
import com.postech.challenge_01.entities.UserAddressEntity;
import com.postech.challenge_01.entities.UserEntity;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import com.postech.challenge_01.mappers.AddressMapper;
import com.postech.challenge_01.repositories.address.AddressRepository;
import com.postech.challenge_01.repositories.user.UserRepository;
import com.postech.challenge_01.repositories.userAddress.UserAddressRepository;
import com.postech.challenge_01.usecases.UseCase;
import com.postech.challenge_01.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SaveUserAddressUseCase implements UseCase<AddressWithUserRequestDTO, AddressResponseDTO>{

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final List<Rule<Address>> rules;
    @Override
    public AddressResponseDTO execute(AddressWithUserRequestDTO addressWithUserRequestDTO) {

        UserEntity user = UserEntity.of(userRepository.findById(addressWithUserRequestDTO.userId())
                .orElseThrow(() -> new UserNotFoundException(addressWithUserRequestDTO.userId())));

        Address entity = AddressMapper.addressWithUserRequestDTOToAddress(addressWithUserRequestDTO);
        rules.forEach(rule -> rule.execute(entity));

        Address savedAddress = addressRepository.save(entity);

        UserAddress userAddress = new UserAddress(null, user.getId(), savedAddress.getId());

        UserAddressEntity link = new UserAddressEntity();
        link.setUser(user);
        link.setAddress(AddressEntity.of(savedAddress));
        userAddressRepository.save((userAddress));

        return AddressMapper.addressToAddressResponseDTO(savedAddress);
    }
}
