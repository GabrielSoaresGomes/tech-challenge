//package com.postech.challenge_01.usecases.address;
//
//import com.postech.challenge_01.application.usecases.address.UpdateAddressUseCase;
//import com.postech.challenge_01.application.usecases.rules.Rule;
//import com.postech.challenge_01.builder.address.AddressBuilder;
//import com.postech.challenge_01.builder.address.AddressRequestDTOBuilder;
//import com.postech.challenge_01.builder.address.AddressUpdateRequestDTOBuilder;
//import com.postech.challenge_01.domain.Address;
//import com.postech.challenge_01.dtos.requests.address.AddressUpdateRequestDTO;
//import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
//import com.postech.challenge_01.exceptions.AddressNotFoundException;
//import com.postech.challenge_01.infrastructure.data_sources.repositories.address.AddressRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//public class UpdateAddressUseCaseTest {
//    private AutoCloseable closeable;
//
//    @Mock
//    private AddressRepository addressRepository;
//
//    @Mock
//    private Rule<Address> ruleMock;
//
//    @InjectMocks
//    private UpdateAddressUseCase updateAddressUseCase;
//
//    @BeforeEach
//    void setUp() {
//        closeable = MockitoAnnotations.openMocks(this);
//        updateAddressUseCase = new UpdateAddressUseCase(addressRepository, List.of(ruleMock));
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        closeable.close();
//    }
//
//    @Test
//    void shouldUpdateAddressSuccessfully() {
//        Long id = 1L;
//        AddressUpdateRequestDTO request = AddressUpdateRequestDTOBuilder
//                .oneAddressUpdateRequestDTO()
//                .withId(id)
//                .withData(AddressRequestDTOBuilder
//                        .oneAddressRequestDTO()
//                        .withStreet("Rua Nova")
//                        .withCity("Cidade Nova")
//                        .build())
//                .build();
//
//        Address updatedAddress = AddressBuilder
//                .oneAddress()
//                .withId(id)
//                .withStreet("Rua Nova")
//                .withCity("Cidade Nova")
//                .withCreatedAt(LocalDateTime.now())
//                .build();
//
//        when(addressRepository.update(any(Address.class), eq(id)))
//                .thenReturn(Optional.of(updatedAddress));
//
//        AddressResponseDTO response = updateAddressUseCase.execute(request);
//
//        verify(ruleMock).execute(any(Address.class));
//        verify(addressRepository, times(1)).update(any(Address.class), eq(id));
//
//        assertThat(response).isNotNull();
//        assertThat(response.id()).isEqualTo(id);
//        assertThat(response.street()).isEqualTo("Rua Nova");
//        assertThat(response.city()).isEqualTo("Cidade Nova");
//    }
//
//    @Test
//    void shouldThrowWhenAddressNotFound() {
//        Long id = 1L;
//        AddressUpdateRequestDTO request = AddressUpdateRequestDTOBuilder
//                .oneAddressUpdateRequestDTO()
//                .withId(id)
//                .build();
//
//        when(addressRepository.update(any(Address.class), eq(id)))
//                .thenReturn(Optional.empty());
//
//        assertThrows(AddressNotFoundException.class,
//                () -> updateAddressUseCase.execute(request));
//
//        verify(addressRepository, times(1)).update(any(Address.class), eq(id));
//    }
//
//    @Test
//    void shouldThrowInvalidRule() {
//        Long id = 1L;
//        AddressUpdateRequestDTO request = AddressUpdateRequestDTOBuilder
//                .oneAddressUpdateRequestDTO()
//                .withId(id)
//                .build();
//
//        doThrow(new RuntimeException("Rule")).when(ruleMock).execute(any(Address.class));
//
//        assertThrows(RuntimeException.class,
//                () -> updateAddressUseCase.execute(request));
//
//        verify(addressRepository, never()).update(any(Address.class), eq(id));
//    }
//}
