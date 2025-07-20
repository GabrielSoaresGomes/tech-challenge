package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.repositories.address.AddressRepository;
import com.postech.challenge_01.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteAddressUseCase implements UseCase<Long, Void> {
    private final AddressRepository addressRepository;

    @Override
    public Void execute(Long id) {
        log.info("Deletando endereço com ID: {}", id);
        Integer delete = this.addressRepository.delete(id);

        if (delete == 0) {
            throw new ResourceNotFoundException("Endereço com ID " + id + " não foi encontrado");
        }

        return null;
    }
}

