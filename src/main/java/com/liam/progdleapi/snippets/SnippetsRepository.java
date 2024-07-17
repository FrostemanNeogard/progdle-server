package com.liam.progdleapi.snippets;

import com.liam.progdleapi.languages.entity.Language;
import com.liam.progdleapi.snippets.entity.Snippet;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface SnippetsRepository extends ListCrudRepository<Snippet, UUID> {
    List<Snippet> findByLanguage(Language language);
}
