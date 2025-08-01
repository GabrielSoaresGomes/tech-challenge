package com.postech.challenge_01.entities;

import com.postech.challenge_01.domains.UserType;
import com.postech.challenge_01.domains.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "users_types")
public class UserTypeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserTypeEnum type;

    @Column(nullable = false)
    private LocalDateTime lastModifiedDateTime;

    @OneToMany(mappedBy = "userType")
    private Set<UserEntity> users;

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    public static UserTypeEntity of(final UserType userType) {
        return new UserTypeEntity(
                userType.getId(),
                userType.getName(),
                userType.getType(),
                userType.getLastModifiedDateTime(),
                null
        );
    }

    public UserType toUserType() {
        return new UserType(
                this.getId(),
                this.getName(),
                this.getType(),
                this.getLastModifiedDateTime()
        );
    }
}
