package com.liam.progdleapi.languages;

import com.liam.progdleapi.languages.entity.Language;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface LanguageRepository extends ListCrudRepository<Language, UUID> {
    Language findByName(String name);
}
