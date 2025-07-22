package com.postech.challenge_01.domains;

import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@EqualsAndHashCode
@ToString
public class Restaurant {
    private Long id;
    private Long ownerId;
    private Long addressId;
    private String name;
    private String type;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime lastModifiedDateTime;

    public Restaurant(
            Long id,
            @NonNull Long ownerId,
            @NonNull Long addressId,
            @NonNull String name,
            @NonNull String type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime,
            @NonNull LocalDateTime lastModifiedDateTime
    ) {
        this.id = id;
        this.ownerId = ownerId;
        this.addressId = addressId;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public Restaurant(
            Long id,
            @NonNull Long ownerId,
            @NonNull Long addressId,
            @NonNull String name,
            @NonNull String type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime
    ) {
        this(id, ownerId, addressId, name, type, startTime, endTime, LocalDateTime.now());
    }

    public Restaurant(
            @NonNull Long ownerId,
            @NonNull Long addressId,
            @NonNull String name,
            @NonNull String type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime
    ) {
        this(null, ownerId, addressId, name, type, startTime, endTime, LocalDateTime.now());
    }
}
