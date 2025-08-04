package com.postech.challenge_01.application.usecases.rules.restaurant;

import com.postech.challenge_01.application.gateways.IUserGateway;
import com.postech.challenge_01.application.gateways.IUserTypeGateway;
import com.postech.challenge_01.application.usecases.rules.Rule;
import com.postech.challenge_01.domain.Restaurant;
import com.postech.challenge_01.domain.enums.UserTypeEnum;
import com.postech.challenge_01.exceptions.UserIsNotOwnerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserIsOwnerRule implements Rule<Restaurant> {
    private final IUserGateway userGateway;
    private final IUserTypeGateway userTypeGateway;

    @Override
    public void execute(Restaurant domain) {
        var userId = domain.getOwnerId();
        var user = userGateway.findById(userId);
        var userType = userTypeGateway.findById(user.getUserTypeId());

        if(userType.getType() != UserTypeEnum.Owner) {
            throw new UserIsNotOwnerException(userId);
        }
    }
}
