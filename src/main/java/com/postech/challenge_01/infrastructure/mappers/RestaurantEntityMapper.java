package com.postech.challenge_01.infrastructure.mappers;

import com.postech.challenge_01.dtos.responses.RestaurantResponseDTO;
import com.postech.challenge_01.dtos.transfer.address.AddressDTO;
import com.postech.challenge_01.dtos.transfer.restaurant.NewRestaurantDTO;
import com.postech.challenge_01.dtos.transfer.restaurant.RestaurantDTO;
import com.postech.challenge_01.infrastructure.entities.AddressEntity;
import com.postech.challenge_01.infrastructure.entities.RestaurantEntity;
import com.postech.challenge_01.infrastructure.entities.UserEntity;

public class RestaurantEntityMapper {
    public static RestaurantEntity toRestaurantEntity(RestaurantDTO source) {
        UserEntity owner = new UserEntity();
        owner.setId(source.ownerId());

        AddressEntity address = new AddressEntity();
        address.setId(source.addressId());

        RestaurantEntity target = new RestaurantEntity();
        target.setId(source.id());
        target.setName(source.name());
        target.setType(source.type());
        target.setStartTime(source.startTime());
        target.setEndTime(source.endTime());
        target.setLastModifiedDateTime(source.lastModifiedDateTime());

        target.setAddress(address);
        target.setOwner(owner);

        return target;
    }

    public static RestaurantEntity toRestaurantEntity(NewRestaurantDTO source) {
        UserEntity owner = new UserEntity();
        owner.setId(source.ownerId());

        AddressEntity address = new AddressEntity();
        address.setId(source.addressId());

        RestaurantEntity target = new RestaurantEntity();
        target.setName(source.name());
        target.setType(source.type());
        target.setStartTime(source.startTime());
        target.setEndTime(source.endTime());

        target.setAddress(address);
        target.setOwner(owner);

        return target;
    }

    public static RestaurantDTO toRestaurantDTO(RestaurantEntity source) {
        return new RestaurantDTO(
                source.getId(),
                source.getOwner().getId(),
                source.getName(),
                source.getType(),
                source.getStartTime(),
                source.getEndTime(),
                source.getAddress().getId(),
                source.getLastModifiedDateTime()
        );
    }

    public static RestaurantResponseDTO toResponseDTO(RestaurantEntity source) {
        AddressEntity addr = source.getAddress();
        AddressDTO addressDto = new AddressDTO(
                addr.getId(),
                addr.getStreet(),
                addr.getNumber(),
                addr.getComplement(),
                addr.getNeighborhood(),
                addr.getCity(),
                addr.getState(),
                addr.getCountry(),
                addr.getPostalCode(),
                addr.getLastModifiedDateTime()
        );

        return new RestaurantResponseDTO(
                source.getId(),
                source.getOwner().getId(),
                source.getName(),
                source.getType(),
                source.getStartTime(),
                source.getEndTime(),
                source.getOwner().getId()
        );
    }
}
