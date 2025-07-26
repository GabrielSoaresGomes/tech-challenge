package com.postech.challenge_01.domains;

import jakarta.validation.constraints.Null;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@EqualsAndHashCode
@ToString
public class Restaurant {
    private Long id;
    private Long ownerId;
    private String name;
    private String type;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime lastModifiedDateTime;
    private Address address;

    public Restaurant(
            Long id,
            @NonNull Long ownerId,
            @NonNull String name,
            @NonNull String type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime,
            @Null LocalDateTime lastModifiedDateTime,
            Address address
    ) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.address = address;
    }

    public Restaurant(
            Long id,
            @NonNull Long ownerId,
            @NonNull String name,
            @NonNull String type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime,
            @Null  LocalDateTime lastModifiedDateTime
    ) {
        this(id, ownerId, name, type, startTime, endTime, lastModifiedDateTime, null);
    }

    public Restaurant(
            Long id,
            @NonNull Long ownerId,
            @NonNull String name,
            @NonNull String type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime,
            @NonNull Address address
    ) {
        this(id, ownerId, name, type, startTime, endTime, LocalDateTime.now(), address);
    }

    public Restaurant(
            @NonNull Long ownerId,
            @NonNull String name,
            @NonNull String type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime,
            @NonNull Address address
    ) {
        this(null, ownerId, name, type, startTime, endTime, LocalDateTime.now(), address);
    }

    public Restaurant(
            Long id,
            @NonNull Long ownerId,
            @NonNull String name,
            @NonNull String type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime
    ) {
        this(id, ownerId, name, type, startTime, endTime, LocalDateTime.now(), null);
    }
}
