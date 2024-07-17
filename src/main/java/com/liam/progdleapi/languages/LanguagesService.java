package com.liam.progdleapi.languages;

import com.liam.progdleapi.languages.entity.Language;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LanguagesService {

    private final LanguageRepository languageRepository;
    public Language CORRECT_LANGUAGE;

    public LanguagesService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
        this.CORRECT_LANGUAGE = languageRepository.findByName("JavaScript");
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Language getLanguageById(UUID id) {
        return languageRepository.findById(id).orElse(null);
    }

    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }

    public Language findLanguageByName(String name) {
        return languageRepository.findByName(name);
    }
}
