package com.liam.progdleapi.snippets.entity;

import com.liam.progdleapi.languages.entity.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "snippets")
public class Snippet {
    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column
    private String content;

    @Column
    private long level;
}
