//package com.postech.challenge_01.usecases.address;
//
//import com.postech.challenge_01.application.usecases.address.FindAllAddressesUseCase;
//import com.postech.challenge_01.builder.address.AddressBuilder;
//import com.postech.challenge_01.domain.Address;
//import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
//import com.postech.challenge_01.infrastructure.data_sources.repositories.address.AddressRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//public class FindAllAddressesUseCaseTest {
//
//    private AutoCloseable closeable;
//
//    @Mock
//    private AddressRepository addressRepository;
//
//    @InjectMocks
//    private FindAllAddressesUseCase findAllAddressesUseCase;
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
//    void shouldReturnListOfAddresses() {
//        Pageable pageable = PageRequest.of(0, 2);
//
//        List<Address> addressList = List.of(
//                AddressBuilder.oneAddress().withId(1L).withStreet("Rua A").withCreatedAt(LocalDateTime.now()).build(),
//                AddressBuilder.oneAddress().withId(2L).withStreet("Rua B").withCreatedAt(LocalDateTime.now()).build()
//        );
//
//        when(addressRepository.findAll(pageable.getPageSize(), pageable.getOffset()))
//                .thenReturn(addressList);
//
//        List<AddressResponseDTO> result = findAllAddressesUseCase.execute(pageable);
//
//        verify(addressRepository, times(1))
//                .findAll(pageable.getPageSize(), pageable.getOffset());
//
//        assertThat(result).isNotNull();
//        assertThat(result).hasSize(2);
//        assertThat(result.get(0).street()).isEqualTo("Rua A");
//        assertThat(result.get(1).street()).isEqualTo("Rua B");
//    }
//
//    @Test
//    void shouldReturnEmptyListWhenNoAddressesExist() {
//        Pageable pageable = PageRequest.of(0, 2);
//
//        when(addressRepository.findAll(pageable.getPageSize(), pageable.getOffset()))
//                .thenReturn(List.of());
//
//        List<AddressResponseDTO> result = findAllAddressesUseCase.execute(pageable);
//
//        verify(addressRepository, times(1))
//                .findAll(pageable.getPageSize(), pageable.getOffset());
//
//        assertThat(result).isNotNull();
//        assertThat(result).isEmpty();
//    }
//}
