package com.liam.progdleapi.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @UuidGenerator
    private UUID id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String profilePictureSrc;

    @Column
    private long score;

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.profilePictureSrc = user.getProfilePictureSrc();
        this.score = user.getScore();
    }
}
