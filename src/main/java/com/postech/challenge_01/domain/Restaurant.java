package com.postech.challenge_01.domain;

import com.postech.challenge_01.domain.enums.RestaurantGenreEnum;
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
    private RestaurantGenreEnum type;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime lastModifiedDateTime;
    private Long addressId;

    public Restaurant(
            Long id,
            @NonNull Long ownerId,
            @NonNull String name,
            @NonNull RestaurantGenreEnum type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime,
            @Null LocalDateTime lastModifiedDateTime,
            @Null Long addressId
    ) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.addressId = addressId;
    }

    public Restaurant(
            Long id,
            @NonNull Long ownerId,
            @NonNull String name,
            @NonNull RestaurantGenreEnum type,
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
            @NonNull RestaurantGenreEnum type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime,
            @NonNull Long addressId
    ) {
        this(id, ownerId, name, type, startTime, endTime, LocalDateTime.now(), addressId);
    }

    public Restaurant(
            @NonNull Long ownerId,
            @NonNull String name,
            @NonNull RestaurantGenreEnum type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime,
            @NonNull Long addressId
    ) {
        this(null, ownerId, name, type, startTime, endTime, LocalDateTime.now(), addressId);
    }

    public Restaurant(
            Long id,
            @NonNull Long ownerId,
            @NonNull String name,
            @NonNull RestaurantGenreEnum type,
            @NonNull LocalTime startTime,
            @NonNull LocalTime endTime
    ) {
        this(id, ownerId, name, type, startTime, endTime, LocalDateTime.now(), null);
    }
}
