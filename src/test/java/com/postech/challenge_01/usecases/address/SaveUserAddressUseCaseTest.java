package com.postech.challenge_01.usecases.address;

import com.postech.challenge_01.application.gateways.IAddressGateway;
import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.usecases.address.SaveUserAddressUseCase;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.builder.address.AddressBuilder;
import com.postech.challenge_01.builder.user_address.NewAddressWithUserRequestDTOBuilder;
import com.postech.challenge_01.domain.Address;
import com.postech.challenge_01.dtos.requests.address.NewAddressWithUserRequestDTO;
import com.postech.challenge_01.infrastructure.entities.UserEntity;
import com.postech.challenge_01.infrastructure.entities.UserTypeEntity;
import com.postech.challenge_01.interface_adapter.gateways.UserAddressGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SaveUserAddressUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private IAddressGateway addressGateway;

    @Mock
    private IUserGateway userGateway;

    @Mock
    private UserAddressGateway userAddressGateway;

    @Mock
    private Rule<Address> ruleMock;

    @InjectMocks
    private SaveUserAddressUseCase saveUserAddressUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        saveUserAddressUseCase = new SaveUserAddressUseCase(addressGateway, userGateway, userAddressGateway, List.of(ruleMock));
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

        NewAddressWithUserRequestDTO requestDTO = NewAddressWithUserRequestDTOBuilder
                .oneRequestDTO()
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

        when(userGateway.findById(userId)).thenReturn(userEntity.toUser());
        when(addressGateway.save(any(Address.class))).thenReturn(savedAddress);

        var response = saveUserAddressUseCase.execute(requestDTO);

        verify(userGateway, times(1)).findById(userId);
        verify(ruleMock).execute(any(Address.class));
        verify(addressGateway, times(1)).save(any(Address.class));
        verify(userAddressGateway, times(1)).save(any());

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(addressId);
        assertThat(response.getStreet()).isEqualTo(requestDTO.street());
        assertThat(response.getCity()).isEqualTo(requestDTO.city());
    }

    @Test
    void shouldThrowInvalidRule() {
        Long userId = 1L;

        NewAddressWithUserRequestDTO requestDTO = NewAddressWithUserRequestDTOBuilder
                .oneRequestDTO()
                .withUserId(userId)
                .build();

        UserEntity userEntity = createValidUserEntity(userId);

        when(userGateway.findById(userId)).thenReturn(userEntity.toUser());

        doThrow(new RuntimeException("Rule")).when(ruleMock).execute(any(Address.class));

        assertThrows(RuntimeException.class, () -> saveUserAddressUseCase.execute(requestDTO));
        verify(addressGateway, never()).save(any(Address.class));
        verify(userAddressGateway, never()).save(any());
    }
}
