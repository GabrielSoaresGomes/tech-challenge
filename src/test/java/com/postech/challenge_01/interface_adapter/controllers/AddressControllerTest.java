package com.postech.challenge_01.interface_adapter.controllers;

import com.postech.challenge_01.application.usecases.address.*;
import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.builder.address.AddressUpdateDataDTOBuilder;
import com.postech.challenge_01.builder.address.AddressUpdateRequestDTOBuilder;
import com.postech.challenge_01.interface_adapter.presenters.AddressPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddressControllerTest {

    @Mock
    private FindAllAddressesUseCase findAllAddressesUseCase;
    @Mock
    private FindAddressByIdUseCase findAddressByIdUseCase;
    @Mock
    private UpdateAddressUseCase updateAddressUseCase;
    @Mock
    private DeleteAddressUseCase deleteAddressUseCase;

    @InjectMocks
    private AddressController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new AddressController(
                findAllAddressesUseCase,
                findAddressByIdUseCase,
                updateAddressUseCase,
                deleteAddressUseCase
        );
    }

    @Test
    void shouldGetAllAddresses() {
        var pageable = PageRequest.of(0, 10);
        var entityList = List.of(AddressBuilder.oneAddress().build());
        var expectedList = AddressPresenter.addressToAddressesResponseDTOList(entityList);

        when(findAllAddressesUseCase.execute(pageable)).thenReturn(entityList);

        var result = controller.getAddress(pageable);

        assertEquals(expectedList, result);
        verify(findAllAddressesUseCase, times(1)).execute(pageable);
    }

    @Test
    void shouldGetAddressById() {
        Long id = 1L;
        var entity = AddressBuilder.oneAddress().withId(id).build();
        var expected = AddressPresenter.addressToAddressResponseDTO(entity);

        when(findAddressByIdUseCase.execute(id)).thenReturn(entity);

        var result = controller.getAddressById(id);

        assertEquals(expected, result);
        verify(findAddressByIdUseCase, times(1)).execute(id);
    }

    @Test
    void shouldUpdateAddress() {
        Long id = 1L;
        var data = AddressUpdateDataDTOBuilder.oneAddressUpdateDataDTO().build();
        var request = AddressUpdateRequestDTOBuilder.oneAddressUpdateRequestDTO()
                .withId(id)
                .withData(data)
                .build();
        var entity = AddressBuilder.oneAddress().withId(id).build();
        var expected = AddressPresenter.addressToAddressResponseDTO(entity);

        when(updateAddressUseCase.execute(request)).thenReturn(entity);

        var result = controller.updateAddress(request);

        assertEquals(expected, result);
        verify(updateAddressUseCase, times(1)).execute(request);
    }

    @Test
    void shouldDeleteAddress() {
        Long id = 1L;

        controller.deleteAddress(id);

        verify(deleteAddressUseCase, times(1)).execute(id);
    }
}
