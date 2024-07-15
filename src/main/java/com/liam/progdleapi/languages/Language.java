package com.liam.progdleapi.languages;

import com.liam.progdleapi.hints.Hint;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
public class Language {
    @Id
    @UuidGenerator
    private UUID id;

    @Column
    private String name;

    @OneToMany(mappedBy = "language")
    private List<Hint> hints;
}
