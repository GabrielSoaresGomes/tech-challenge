package com.postech.challenge_01.application.usecases.address;

import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.requests.address.AddressWithUserRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.application.mappers.AddressMapper;
import com.postech.challenge_01.infrastructure.data_sources.repositories.address.AddressRepository;
import com.postech.challenge_01.interface_adapter.data_sources.repositories.UserRepository;
import com.postech.challenge_01.infrastructure.data_sources.repositories.user_address.UserAddressRepository;
import com.postech.challenge_01.application.usecases.UseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SaveUserAddressUseCase implements UseCase<AddressWithUserRequestDTO, AddressResponseDTO>{

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final List<Rule<Address>> rules;
    @Override
    public AddressResponseDTO execute(AddressWithUserRequestDTO addressWithUserRequestDTO) {

//        UserEntity user = UserEntity.of(userRepository.findById(addressWithUserRequestDTO.userId())
//                .orElseThrow(() -> new UserNotFoundException(addressWithUserRequestDTO.userId())));

        Address entity = AddressMapper.addressWithUserRequestDTOToAddress(addressWithUserRequestDTO);
        rules.forEach(rule -> rule.execute(entity));

//        Address savedAddress = addressRepository.save(entity);
//
//        UserAddressEntity link = new UserAddressEntity();
//        link.setUser(user);
//        link.setAddress(AddressEntity.of(savedAddress));
//        userAddressRepository.save(link.toUserAddress());
//
//        return AddressMapper.addressToAddressResponseDTO(savedAddress);
        return null;
    }
}
