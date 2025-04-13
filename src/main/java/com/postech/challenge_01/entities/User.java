package com.postech.challenge_01.entities;

import com.postech.challenge_01.dtos.requests.UserRequestDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    private Long id;
    private String name;
    private String email;
    private String login;
    private String password;

    public User(UserRequestDTO user) {
        this.name = user.name();
        this.email = user.email();
        this.login = user.login();
        this.password = user.password();
    }
}
