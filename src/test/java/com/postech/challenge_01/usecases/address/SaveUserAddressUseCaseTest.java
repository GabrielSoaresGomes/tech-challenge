package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.builder.address.AddressWithUserRequestDTOBuilder;
import com.postech.challenge_01.domains.Address;
import com.postech.challenge_01.dtos.requests.address.AddressWithUserRequestDTO;
import com.postech.challenge_01.dtos.responses.AddressResponseDTO;
import com.postech.challenge_01.entities.UserEntity;
import com.postech.challenge_01.entities.UserTypeEntity;
import com.postech.challenge_01.exceptions.UserNotFoundException;
import com.postech.challenge_01.repositories.address.AddressRepository;
import com.postech.challenge_01.repositories.user.UserRepository;
import com.postech.challenge_01.repositories.user_address.UserAddressRepository;
import com.postech.challenge_01.usecases.rules.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SaveUserAddressUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserAddressRepository userAddressRepository;

    @Mock
    private Rule<Address> ruleMock;

    @InjectMocks
    private SaveUserAddressUseCase saveUserAddressUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        saveUserAddressUseCase = new SaveUserAddressUseCase(addressRepository, userRepository, userAddressRepository, List.of(ruleMock));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    private UserEntity createValidUserEntity(Long userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setName("Test User");
        userEntity.setEmail("test@test.com");
        userEntity.setLogin("test.login");
        userEntity.setPassword("123456");
        userEntity.setLastModifiedDateTime(java.time.LocalDateTime.now());

        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setId(10L);
        userEntity.setUserType(userTypeEntity);

        return userEntity;
    }

    @Test
    void shouldExecuteAndSaveUserAddressSuccessfully() {
        Long userId = 1L;
        Long addressId = 2L;

        AddressWithUserRequestDTO requestDTO = AddressWithUserRequestDTOBuilder
                .oneAddressWithUserRequestDTO()
                .withUserId(userId)
                .build();

        Address savedAddress = AddressBuilder
                .oneAddress()
                .withId(addressId)
                .withCreatedAt(LocalDateTime.now())
                .withStreet(requestDTO.street())
                .withCity(requestDTO.city())
                .build();


        UserEntity userEntity = createValidUserEntity(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity.toUser()));
        when(addressRepository.save(any(Address.class))).thenReturn(savedAddress);

        AddressResponseDTO response = saveUserAddressUseCase.execute(requestDTO);

        verify(userRepository, times(1)).findById(userId);
        verify(ruleMock).execute(any(Address.class));
        verify(addressRepository, times(1)).save(any(Address.class));
        verify(userAddressRepository, times(1)).save(any());

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(addressId);
        assertThat(response.street()).isEqualTo(requestDTO.street());
        assertThat(response.city()).isEqualTo(requestDTO.city());
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        Long userId = 99L;

        AddressWithUserRequestDTO requestDTO = AddressWithUserRequestDTOBuilder
                .oneAddressWithUserRequestDTO()
                .withUserId(userId)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> saveUserAddressUseCase.execute(requestDTO));
        verify(addressRepository, never()).save(any(Address.class));
        verify(userAddressRepository, never()).save(any());
    }

    @Test
    void shouldThrowInvalidRule() {
        Long userId = 1L;

        AddressWithUserRequestDTO requestDTO = AddressWithUserRequestDTOBuilder
                .oneAddressWithUserRequestDTO()
                .withUserId(userId)
                .build();

        UserEntity userEntity = createValidUserEntity(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity.toUser()));

        doThrow(new RuntimeException("Rule")).when(ruleMock).execute(any(Address.class));

        assertThrows(RuntimeException.class, () -> saveUserAddressUseCase.execute(requestDTO));
        verify(addressRepository, never()).save(any(Address.class));
        verify(userAddressRepository, never()).save(any());
    }
}
