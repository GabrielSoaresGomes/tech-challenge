package com.postech.challenge_01.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class UserAddress {
    private Long id;
    private Long userId;
    private Long addressId;

    public UserAddress(
            Long id,
            @NonNull Long userId,
            @NonNull Long addressId
    ) {
        this.id = id;
        this.userId = userId;
        this.addressId = addressId;
    }
}
