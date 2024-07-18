package com.liam.progdleapi.languages;

import com.liam.progdleapi.languages.entity.Language;
import com.liam.progdleapi.languages.entity.LanguageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/languages")
public class LanguagesController {

    private final LanguagesService languagesService;

    public LanguagesController(LanguagesService languagesService) {
        this.languagesService = languagesService;
    }

    @GetMapping
    public ResponseEntity<List<LanguageDto>> getLanguages() {
        List<Language> languages = this.languagesService.getAllLanguages();
        List<LanguageDto> languageDtos = languages.stream().map(LanguageDto::from).toList();
        return ResponseEntity.ok(languageDtos);
    }

    @PostMapping
    public ResponseEntity<LanguageDto> createLanguage(@RequestBody LanguageDto languageDto) {
        Language language = new Language(languageDto);
        Language newLanguage = this.languagesService.createLanguage(language);
        return ResponseEntity
                .created(URI.create("/api/languages/" + newLanguage.getId()))
                .body(LanguageDto.from(newLanguage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable UUID id) {
        boolean isDeleted = this.languagesService.deleteLanguageById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
