package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long userTypeId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 50)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime lastModifiedDateTime;

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    public static UserEntity of(final User user) {
        return new UserEntity(
                user.getId(),
                user.getUserTypeId(),
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
                this.getUserTypeId(),
                this.getName(),
                this.getEmail(),
                this.getLogin(),
                this.getPassword(),
                this.getLastModifiedDateTime()
        );
    }
}
