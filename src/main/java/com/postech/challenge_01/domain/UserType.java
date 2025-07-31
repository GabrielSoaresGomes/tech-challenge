package com.postech.challenge_01.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class UserType {
    private Long id;
    private String name;
    private String type;
    private LocalDateTime lastModifiedDateTime;

    public UserType(
            Long id,
            @NonNull String name,
            @NonNull String type,
            @NonNull LocalDateTime lastModifiedDateTime
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public UserType(
            Long id,
            @NonNull String name,
            @NonNull String type
    ) {
        this(id, name, type, LocalDateTime.now());
    }

    public UserType(
            @NonNull String name,
            @NonNull String type
    ) {
        this(null, name, type, LocalDateTime.now());
    }
}
