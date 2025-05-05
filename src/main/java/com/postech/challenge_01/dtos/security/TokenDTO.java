package com.postech.challenge_01.dtos.security;

import java.util.Date;

public record TokenDTO (
        String login,
        Boolean authenticated,
        Date created,
        Date expiration
){}