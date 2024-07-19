package com.liam.progdleapi.snippets;

import com.liam.progdleapi.languages.LanguagesService;
import com.liam.progdleapi.languages.entity.Language;
import com.liam.progdleapi.snippets.entity.Snippet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SnippetsService {

    private final SnippetsRepository snippetsRepo;
    private final LanguagesService languagesService;

    public SnippetsService(SnippetsRepository snippetsRepo, LanguagesService languagesService) {
        this.snippetsRepo = snippetsRepo;
        this.languagesService = languagesService;
    }

    public List<Snippet> getAllSnippets() {
        return this.snippetsRepo.findAll();
    }

    public List<Snippet> getSnippetsByLanguageId(UUID languageId) {
        Language language = languagesService.getLanguageById(languageId);
        if (language == null) {
            return null;
        }
        return this.snippetsRepo.findByLanguage(language);
    }

    public Snippet createSnippet(Snippet snippet) {
        List<Snippet> existingSnippets = this.snippetsRepo.findByLanguage(snippet.getLanguage());
        existingSnippets
                .stream()
                .filter(e -> e.getLevel() == snippet.getLevel())
                .findFirst()
                .ifPresent(this.snippetsRepo::delete);
        return this.snippetsRepo.save(snippet);
    }

    public List<Snippet> getDailySnippets() {
        return this.snippetsRepo.findByLanguage(languagesService.getDailyLanguage());
    }
}
