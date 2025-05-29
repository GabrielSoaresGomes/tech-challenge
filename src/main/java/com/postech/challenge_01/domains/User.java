package com.postech.challenge_01.domains;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class User {
    private Long id;
    private String name;
    private String email;
    private String login;
    private String password;
    private String address;
    private LocalDateTime lastModifiedDateTime;

    public User(
            Long id,
            @NonNull String name,
            String email,
            @NonNull String login,
            @NonNull String password,
            String address,
            @NonNull LocalDateTime lastModifiedDateTime
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.address = address;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public User(
            Long id,
            @NonNull String name,
            String email,
            @NonNull String login,
            @NonNull String password,
            String address
    ) {
        this(id, name, email, login, password, address, LocalDateTime.now());
    }

    public User(
            @NonNull String name,
            String email,
            @NonNull String login,
            @NonNull String password,
            String address
    ) {
        this(null, name, email, login, password, address, LocalDateTime.now());
    }
}
