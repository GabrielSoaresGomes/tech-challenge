//package com.postech.challenge_01.usecases.restaurant;
//
//import com.postech.challenge_01.builder.restaurant.RestaurantBuilder;
//import com.postech.challenge_01.builder.restaurant.RestaurantRequestDTOBuilder;
//import com.postech.challenge_01.domain.Address;
//import com.postech.challenge_01.domain.Restaurant;
//import com.postech.challenge_01.dtos.requests.restaurant.RestaurantRequestDTO;
//import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
//import com.postech.challenge_01.infrastructure.data_sources.repositories.address.AddressRepository;
//import com.postech.challenge_01.infrastructure.data_sources.repositories.restaurant.RestaurantRepository;
//import com.postech.challenge_01.application.usecases.restaurant.SaveRestaurantUseCase;
//import com.postech.challenge_01.application.usecases.rules.Rule;
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
//public class SaveRestaurantUseCaseTest {
//    private AutoCloseable closeable;
//
//    @Mock
//    private RestaurantRepository restaurantRepository;
//
//    @Mock
//    private AddressRepository addressRepository;
//
//    @Mock
//    private Rule<Restaurant> ruleMock;
//
//    @InjectMocks
//    private SaveRestaurantUseCase saveRestaurantUseCase;
//
//    @BeforeEach
//    void setUp() {
//        closeable = MockitoAnnotations.openMocks(this);
//        saveRestaurantUseCase = new SaveRestaurantUseCase(restaurantRepository, addressRepository, List.of(ruleMock));
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        closeable.close();
//    }
//
//    @Test
//    void shouldExecuteAndSaveRestaurantSuccessfully() {
//        // Arrange
//        Long id = 1L;
//        // TODO - Trocar para o Builder de Address quando tiver
//        Address savedAddress = new Address(1L, "Rua Teste", "Número teste",
//                "Complemento teste", "Bairro Teste", "Cidade Teste", "Estado Teste",
//                "País Teste", "CEP Teste",
//                LocalDateTime.of(2025, 7, 24, 23, 50, 0, 0)
//        );
//
//        RestaurantRequestDTO requestDTO = RestaurantRequestDTOBuilder
//                .oneRestaurantRequestDTO()
//                .build();
//
//        Restaurant savedRestaurant = RestaurantBuilder
//                .oneRestaurant()
//                .withId(id)
//                .build();
//
//        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(savedRestaurant);
//        when(addressRepository.save(any(Address.class))).thenReturn(savedAddress);
//
//        // Act
//        RestaurantResponseDTO response = saveRestaurantUseCase.execute(requestDTO);
//
//        // Assert
//        verify(ruleMock).execute(any(Restaurant.class));
//        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
//
//        assertThat(response).isNotNull();
//        assertThat(response.id()).isEqualTo(id);
//        assertThat(response.ownerId()).isEqualTo(requestDTO.ownerId());
//        assertThat(response.address().getStreet()).isEqualTo(requestDTO.address().getStreet());
//        assertThat(response.address().getNumber()).isEqualTo(requestDTO.address().getNumber());
//        assertThat(response.address().getComplement()).isEqualTo(requestDTO.address().getComplement());
//        assertThat(response.address().getNeighborhood()).isEqualTo(requestDTO.address().getNeighborhood());
//        assertThat(response.address().getCity()).isEqualTo(requestDTO.address().getCity());
//        assertThat(response.address().getState()).isEqualTo(requestDTO.address().getState());
//        assertThat(response.address().getCountry()).isEqualTo(requestDTO.address().getCountry());
//        assertThat(response.address().getPostalCode()).isEqualTo(requestDTO.address().getPostalCode());
//        assertThat(response.name()).isEqualTo(requestDTO.name());
//        assertThat(response.type()).isEqualTo(requestDTO.type());
//        assertThat(response.startTime()).isEqualTo(requestDTO.startTime());
//        assertThat(response.endTime()).isEqualTo(requestDTO.endTime());
//    }
//
//    @Test
//    void shouldThrowInvalidRule() {
//        // Arrange
//        RestaurantRequestDTO requestDTO = RestaurantRequestDTOBuilder
//                .oneRestaurantRequestDTO()
//                .build();
//
//        doThrow(new RuntimeException("Rule")).when(ruleMock).execute(any(Restaurant.class));
//
//        // Assert
//        assertThrows(RuntimeException.class, () -> saveRestaurantUseCase.execute(requestDTO));
//        verify(restaurantRepository, never()).save(any(Restaurant.class));
//    }
//}
