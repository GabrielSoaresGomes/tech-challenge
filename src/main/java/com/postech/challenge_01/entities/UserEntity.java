package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserEntity {
    private Long id;
    private String name;
    private String email;
    private String login;
    private String password;
    private LocalDateTime lastModifiedDateTime;

    public static UserEntity of(final User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword(),
                user.getLastModifiedDateTime()
        );
    }

    public User toUser() {
        return new User(
                this.getId(),
                this.getName(),
                this.getEmail(),
                this.getLogin(),
                this.getPassword(),
                this.getLastModifiedDateTime()
        );
    }
}
