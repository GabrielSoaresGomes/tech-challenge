//package com.postech.challenge_01.usecases.address;
//
//import com.postech.challenge_01.application.usecases.address.DeleteAddressUseCase;
//import com.postech.challenge_01.exceptions.ResourceNotFoundException;
//import com.postech.challenge_01.infrastructure.data_sources.repositories.address.AddressRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.Mockito.*;
//
//public class DeleteAddressUseCaseTest {
//
//    private AutoCloseable closeable;
//
//    @Mock
//    private AddressRepository addressRepository;
//
//    @InjectMocks
//    private DeleteAddressUseCase deleteAddressUseCase;
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
//    void shouldDeleteAddressSuccessfully() {
//        Long id = 1L;
//        when(addressRepository.delete(id)).thenReturn(1);
//
//        deleteAddressUseCase.execute(id);
//
//        verify(addressRepository, times(1)).delete(id);
//    }
//
//    @Test
//    void shouldThrowWhenAddressNotFound() {
//        Long id = 1L;
//        when(addressRepository.delete(id)).thenReturn(0);
//
//        assertThatThrownBy(() -> deleteAddressUseCase.execute(id))
//                .isInstanceOf(ResourceNotFoundException.class)
//                .hasMessage("Endereço com ID " + id + " não foi encontrado");
//
//        verify(addressRepository, times(1)).delete(id);
//    }
//}
