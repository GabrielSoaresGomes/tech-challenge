package com.postech.challenge_01.domains;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class User {
    private Long id;
    private Long userTypeId;
    private String name;
    private String email;
    private String login;
    private String password;
    private LocalDateTime lastModifiedDateTime;

    public User(
            Long id,
            @NonNull Long userTypeId,
            @NonNull String name,
            String email,
            @NonNull String login,
            @NonNull String password,
            @NonNull LocalDateTime lastModifiedDateTime
    ) {
        this.id = id;
        this.userTypeId = userTypeId;
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public User(
            Long id,
            @NonNull Long userTypeId,
            @NonNull String name,
            String email,
            @NonNull String login,
            @NonNull String password
    ) {
        this(id, userTypeId, name, email, login, password, LocalDateTime.now());
    }

    public User(
            @NonNull Long userTypeId,
            @NonNull String name,
            String email,
            @NonNull String login,
            @NonNull String password
    ) {
        this(null, userTypeId, name, email, login, password, LocalDateTime.now());
    }
}
