package com.liam.progdleapi.languages.entity;

import com.liam.progdleapi.snippets.entity.Snippet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "languages")
public class Language {
    @Id
    @UuidGenerator
    private UUID id;

    @Column
    private String name;

    @Column
    private long releaseYear;

    @Column
    private String paradigm;

    @Column
    private String typing;

    @Column
    private String domain;

    @Column
    private boolean memorySafe;

    @Column
    private String os;

    @OneToMany(mappedBy = "language")
    private List<Snippet> snippets;

    public Language(LanguageDto dto) {
        this.domain = dto.domain();
        this.name = dto.name();
        this.paradigm = dto.paradigm();
        this.typing = dto.typing();
        this.releaseYear = dto.releaseYear();
        this.memorySafe = dto.memorySafe();
        this.os = dto.os();
    }
}
