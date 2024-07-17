package com.liam.progdleapi.snippets;

import com.liam.progdleapi.snippets.entity.Snippet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetsService {

    private final SnippetsRepository snippetsRepo;

    public SnippetsService(SnippetsRepository snippetsRepo) {
        this.snippetsRepo = snippetsRepo;
    }

    public List<Snippet> getAllSnippets() {
        return this.snippetsRepo.findAll();
    }
}
