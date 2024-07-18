package com.liam.progdleapi.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
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

    @Column
    private long guessesMade;

    @Column
    private boolean hasWon;
}
