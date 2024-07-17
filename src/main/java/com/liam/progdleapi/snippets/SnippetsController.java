package com.liam.progdleapi.snippets;

import com.liam.progdleapi.languages.LanguagesService;
import com.liam.progdleapi.languages.entity.Language;
import com.liam.progdleapi.snippets.entity.CreateSnippetDto;
import com.liam.progdleapi.snippets.entity.Snippet;
import com.liam.progdleapi.snippets.entity.SnippetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/snippets")
public class SnippetsController {

    private final SnippetsService snippetsService;
    private final LanguagesService languagesService;

    public SnippetsController(SnippetsService snippetsService, LanguagesService languagesService) {
        this.snippetsService = snippetsService;
        this.languagesService = languagesService;
    }

    @GetMapping
    public ResponseEntity<List<SnippetDto>> getSnippets() {
        List<Snippet> snippets = this.snippetsService.getAllSnippets();
        return ResponseEntity.ok(snippets.stream().map(SnippetDto::from).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SnippetDto>> getSnippetsByLanguageId(@PathVariable UUID id) {
        List<Snippet> snippets = this.snippetsService.getSnippetsByLanguageId(id);
        return ResponseEntity.ok(snippets.stream().map(SnippetDto::from).toList());
    }

    @GetMapping("/daily/{level}")
    public ResponseEntity<SnippetDto> getDailySnippetByLevel(@PathVariable Integer level) {
        if (level < 1 || level > 5) {
            return ResponseEntity.badRequest().build();
        }
        List<Snippet> snippets = this.snippetsService.getDailySnippets();
        System.out.println(snippets);
        return ResponseEntity.ok(SnippetDto.from(snippets.get(level - 1)));
    }

    @PostMapping
    public ResponseEntity<SnippetDto> createSnippet(@RequestBody CreateSnippetDto dto) {
        Language language = languagesService.getLanguageById(dto.languageId());
        if (language == null) {
            return ResponseEntity.notFound().build();
        }

        Snippet snippet = new Snippet();
        snippet.setLanguage(language);
        snippet.setContent(dto.content());
        snippet.setLevel(dto.level());

        Snippet newSnippet = this.snippetsService.createSnippet(snippet);
        return ResponseEntity.created(URI.create("/api/snippets/" + newSnippet.getId())).body(SnippetDto.from(newSnippet));
    }
}
