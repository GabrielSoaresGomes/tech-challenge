//package com.postech.challenge_01.usecases.address;
//
//import com.postech.challenge_01.application.usecases.address.SaveAddressUseCase;
//import com.postech.challenge_01.application.usecases.rules.Rule;
//import com.postech.challenge_01.builder.address.AddressBuilder;
//import com.postech.challenge_01.builder.address.AddressRequestDTOBuilder;
//import com.postech.challenge_01.domain.Address;
//import com.postech.challenge_01.dtos.requests.address.AddressRequestDTO;
//import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
//import com.postech.challenge_01.infrastructure.data_sources.repositories.address.AddressRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class SaveAddressUseCaseTest {
//    private AutoCloseable closeable;
//
//    @Mock
//    private AddressRepository addressRepository;
//
//    @Mock
//    private Rule<Address> ruleMock;
//
//    @InjectMocks
//    private SaveAddressUseCase saveAddressUseCase;
//
//    @BeforeEach
//    void setUp() {
//        closeable = MockitoAnnotations.openMocks(this);
//        saveAddressUseCase = new SaveAddressUseCase(addressRepository, List.of(ruleMock));
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        closeable.close();
//    }
//
//    @Test
//    void shouldExecuteAndSaveAddressSuccessfully() {
//        Long id = 1L;
//
//        AddressRequestDTO requestDTO = AddressRequestDTOBuilder
//                .oneAddressRequestDTO()
//                .build();
//
//        Address savedAddress = AddressBuilder
//                .oneAddress()
//                .withId(id)
//                .withStreet(requestDTO.street())
//                .withNumber(requestDTO.number())
//                .withComplement(requestDTO.complement())
//                .withNeighborhood(requestDTO.neighborhood())
//                .withCity(requestDTO.city())
//                .withState(requestDTO.state())
//                .withCountry(requestDTO.country())
//                .withPostalCode(requestDTO.postalCode())
//                .withCreatedAt(LocalDateTime.now())
//                .build();
//
//        when(addressRepository.save(any(Address.class))).thenReturn(savedAddress);
//
//        AddressResponseDTO response = saveAddressUseCase.execute(requestDTO);
//
//        verify(ruleMock).execute(any(Address.class));
//        verify(addressRepository, times(1)).save(any(Address.class));
//
//        assertThat(response).isNotNull();
//        assertThat(response.id()).isEqualTo(id);
//        assertThat(response.street()).isEqualTo(requestDTO.street());
//        assertThat(response.number()).isEqualTo(requestDTO.number());
//        assertThat(response.complement()).isEqualTo(requestDTO.complement());
//        assertThat(response.neighborhood()).isEqualTo(requestDTO.neighborhood());
//        assertThat(response.city()).isEqualTo(requestDTO.city());
//        assertThat(response.state()).isEqualTo(requestDTO.state());
//        assertThat(response.country()).isEqualTo(requestDTO.country());
//        assertThat(response.postalCode()).isEqualTo(requestDTO.postalCode());
//    }
//
//    @Test
//    void shouldThrowInvalidRule() {
//        AddressRequestDTO requestDTO = AddressRequestDTOBuilder
//                .oneAddressRequestDTO()
//                .build();
//
//        doThrow(new RuntimeException("Rule")).when(ruleMock).execute(any(Address.class));
//
//        assertThrows(RuntimeException.class, () -> saveAddressUseCase.execute(requestDTO));
//        verify(addressRepository, never()).save(any(Address.class));
//    }
//}
