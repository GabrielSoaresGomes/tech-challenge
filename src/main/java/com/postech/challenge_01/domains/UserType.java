package com.postech.challenge_01.domains;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class UserType {
    private Long id;
    private String name;
    private LocalDateTime lastModifiedDateTime;

    public UserType(
            Long id,
            @NonNull String name,
            @NonNull LocalDateTime lastModifiedDateTime
    ) {
        this.id = id;
        this.name = name;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public UserType(
            Long id,
            @NonNull String name
    ) {
        this(id, name, LocalDateTime.now());
    }

    public UserType(
            @NonNull String name
    ) {
        this(null, name, LocalDateTime.now());
    }
}
