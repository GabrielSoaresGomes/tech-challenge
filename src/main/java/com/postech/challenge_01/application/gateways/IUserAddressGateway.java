package com.postech.challenge_01.application.gateways;

import com.postech.challenge_01.domain.UserAddress;

public interface IUserAddressGateway {
    UserAddress save(UserAddress entity);
}
