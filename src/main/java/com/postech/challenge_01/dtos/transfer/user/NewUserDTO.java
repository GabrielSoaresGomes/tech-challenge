package com.postech.challenge_01.dtos.transfer.user;

public record NewUserDTO(
        Long userTypeId,
        String name,
        String email,
        String login,
        String password
) {}
