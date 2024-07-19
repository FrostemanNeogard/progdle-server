package com.liam.progdleapi.languages;

import com.liam.progdleapi.languages.entity.Language;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class LanguagesService {

    private final LanguageRepository languageRepository;
    private Language CORRECT_LANGUAGE;

    public LanguagesService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language getDailyLanguage() {
        // Random random = new Random(LocalDateTime.now().getDayOfMonth() + LocalDateTime.now().getYear());
        Random random = new Random();
        if (CORRECT_LANGUAGE == null) {
            List<Language> allLanguages = languageRepository.findAll();
            CORRECT_LANGUAGE = allLanguages.get(random.nextInt(allLanguages.size()));
        }
        return CORRECT_LANGUAGE;
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

    public boolean deleteLanguageById(UUID id) {
        Language language = languageRepository.findById(id).orElse(null);
        if (language == null) {
            return false;
        }
        languageRepository.delete(language);
        return true;
    }
}
