//package com.postech.challenge_01.usecases.address;
//
//import com.postech.challenge_01.application.usecases.address.FindAddressByIdUseCase;
//import com.postech.challenge_01.builder.address.AddressBuilder;
//import com.postech.challenge_01.domain.Address;
//import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
//import com.postech.challenge_01.exceptions.ResourceNotFoundException;
//import com.postech.challenge_01.infrastructure.data_sources.repositories.address.AddressRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//public class FindAddressByIdUseCaseTest {
//
//    private AutoCloseable closeable;
//
//    @Mock
//    private AddressRepository addressRepository;
//
//    @InjectMocks
//    private FindAddressByIdUseCase findAddressByIdUseCase;
//
//    @BeforeEach
//    void setUp() {
//        closeable = MockitoAnnotations.openMocks(this);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        closeable.close();
//    }
//
//    @Test
//    void shouldReturnAddressSuccessfully() {
//        Long id = 1L;
//
//        Address address = AddressBuilder
//                .oneAddress()
//                .withId(id)
//                .withStreet("Rua Teste")
//                .withCreatedAt(LocalDateTime.now())
//                .build();
//
//        when(addressRepository.findById(id))
//                .thenReturn(Optional.of(address));
//
//        AddressResponseDTO response = findAddressByIdUseCase.execute(id);
//
//        verify(addressRepository, times(1)).findById(id);
//
//        assertThat(response).isNotNull();
//        assertThat(response.id()).isEqualTo(id);
//        assertThat(response.street()).isEqualTo("Rua Teste");
//    }
//
//    @Test
//    void shouldThrowWhenAddressNotFound() {
//        Long id = 1L;
//
//        when(addressRepository.findById(id))
//                .thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class,
//                () -> findAddressByIdUseCase.execute(id));
//
//        verify(addressRepository, times(1)).findById(id);
//    }
//}
