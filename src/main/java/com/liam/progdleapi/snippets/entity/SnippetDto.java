package com.liam.progdleapi.snippets.entity;

import java.util.UUID;

public record SnippetDto(UUID languageId, String content, long level) {
    public static SnippetDto from(Snippet snippet) {
        return new SnippetDto(snippet.getLanguage().getId(), snippet.getContent(), snippet.getLevel());
    }
}
