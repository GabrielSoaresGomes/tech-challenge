package com.postech.challenge_01.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postech.challenge_01.domains.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    // Campo técnico para acessar o ID diretamente, sem depender da entidade UserTypeEntity
    @Column(name = "userTypeId", insertable = false, updatable = false)
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userTypeId", nullable = false)
    private UserTypeEntity userType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAddressEntity> userAddresses = new HashSet<>();

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    public static UserEntity of(final User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setLogin(user.getLogin());
        entity.setPassword(user.getPassword());
        entity.setLastModifiedDateTime(user.getLastModifiedDateTime());

        entity.setUserTypeId(user.getUserTypeId());

        return entity;
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
