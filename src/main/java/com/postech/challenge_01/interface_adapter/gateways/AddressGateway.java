package com.postech.challenge_01.interface_adapter.gateways;

import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.exceptions.AddressNotFoundException;
import com.postech.challenge_01.infrastructure.data_sources.repositories.address.AddressRepository;
import com.postech.challenge_01.mappers.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressGateway implements IAddressGateway {

    private final AddressRepository repository;

    @Override
    public Address findById(Long id) {
        var addressDTO = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado para o id " + id));

        return AddressMapper.toAddress(addressDTO);
    }

    @Override
    public List<Address> findAll(Pageable pageable) {
        return repository.findAll(pageable.getPageSize(), pageable.getOffset())
                .stream()
                .map(AddressMapper::toAddress)
                .toList();
    }

    @Override
    public Address save(Address entity) {
        var newAddressDTO = AddressMapper.toNewAddressDTO(entity);
        var savedAddressDTO = repository.save(newAddressDTO);
        return AddressMapper.toAddress(savedAddressDTO);
    }

    @Override
    public Address update(Address entity, Long id) {
        var addressDTO = AddressMapper.toAddressDTO(entity, id);
        var savedAddressDTO = repository.update(addressDTO)
                .orElseThrow(() -> new AddressNotFoundException(id));

        return AddressMapper.toAddress(savedAddressDTO);
    }

    @Override
    public void delete(Long id) {
        var wasDeleted = repository.delete(id);
        if (wasDeleted == 0) {
            throw new AddressNotFoundException(id);
        }
    }

    @Override
    public List<Address> findAllByUserId(Long userId, int size, long offset) {
        return List.of();
    }

    @Override
    public Optional<Address> findByIdAndUserId(Long id, Long userId) {
        return Optional.empty();
    }

    @Override
    public void deleteByUserId(Long userId) {

    }

    @Override
    public void deleteByRestaurantId(Long restaurantId) {
        // TODO - Implementar
    }
}
