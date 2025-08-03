package com.postech.challenge_01.infrastructure.mappers;

import com.postech.challenge_01.dtos.transfer.user_address.NewUserAddressDTO;
import com.postech.challenge_01.dtos.transfer.user_address.UserAddressDTO;
import com.postech.challenge_01.infrastructure.entities.AddressEntity;
import com.postech.challenge_01.infrastructure.entities.UserAddressEntity;
import com.postech.challenge_01.infrastructure.entities.UserEntity;

public class UserAddressEntityMapper {
    public static UserAddressEntity toUserAddressEntity(UserAddressDTO source) {
        UserEntity user = new UserEntity();
        AddressEntity address = new AddressEntity();

        user.setId(source.userId());
        address.setId(source.addressId());

        return new UserAddressEntity(source.id(), user, address);
    }

    public static UserAddressEntity toUserAddressEntity(NewUserAddressDTO source) {
        UserAddressEntity target = new UserAddressEntity();
        UserEntity user = new UserEntity();
        AddressEntity address = new AddressEntity();

        user.setId(source.userId());
        address.setId(source.addressId());

        target.setUser(user);
        target.setAddress(address);

        return target;
    }

    public static UserAddressDTO toUserAddressDTO(UserAddressEntity source) {
        return new UserAddressDTO(
                source.getId(),
                source.getUser().getId(),
                source.getAddress().getId()
        );
    }
}
