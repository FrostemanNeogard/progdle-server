package com.liam.progdleapi.snippets;

import com.liam.progdleapi.snippets.entity.Snippet;
import com.liam.progdleapi.snippets.entity.SnippetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/snippets")
public class SnippetsController {

    private final SnippetsService snippetsService;

    public SnippetsController(SnippetsService snippetsService) {
        this.snippetsService = snippetsService;
    }

    @GetMapping
    public ResponseEntity<List<SnippetDto>> getSnippets() {
        List<Snippet> snippets = this.snippetsService.getAllSnippets();
        return ResponseEntity.ok(snippets.stream().map(SnippetDto::from).toList());
    }

}
