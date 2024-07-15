package com.liam.progdleapi.hints;

import com.liam.progdleapi.languages.Language;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Hint {
    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column
    private String content;
}
