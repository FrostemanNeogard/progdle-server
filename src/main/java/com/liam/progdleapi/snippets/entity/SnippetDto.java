package com.liam.progdleapi.snippets.entity;

public record SnippetDto(String content, long level) {
    public static SnippetDto from(Snippet snippet) {
        return new SnippetDto(snippet.getContent(), snippet.getLevel());
    }
}
