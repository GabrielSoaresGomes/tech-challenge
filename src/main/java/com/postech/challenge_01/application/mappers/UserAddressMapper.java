package com.postech.challenge_01.application.mappers;

import com.postech.challenge_01.domain.UserAddress;
import com.postech.challenge_01.dtos.transfer.user_address.NewUserAddressDTO;
import com.postech.challenge_01.dtos.transfer.user_address.UserAddressDTO;

public class UserAddressMapper {
    public static UserAddress toUserAddress(UserAddressDTO source) {
        return new UserAddress(source.id(), source.userId(), source.addressId());
    }

    public static UserAddress toUserAddress(Long userId, Long addressId) {
        return new UserAddress(userId, addressId);
    }

    public static NewUserAddressDTO toNewUserAddressDTO(UserAddress source) {
        return new NewUserAddressDTO(source.getUserId(), source.getAddressId());
    }
}
