package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.UserType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserTypeEntity {
    private Long id;
    private String name;
    private LocalDateTime lastModifiedDateTime;

    public static UserTypeEntity of(final UserType userType) {
        return new UserTypeEntity(
                userType.getId(),
                userType.getName(),
                userType.getLastModifiedDateTime()
        );
    }

    public UserType toUserType() {
        return new UserType(
                this.getId(),
                this.getName(),
                this.getLastModifiedDateTime()
        );
    }
}
