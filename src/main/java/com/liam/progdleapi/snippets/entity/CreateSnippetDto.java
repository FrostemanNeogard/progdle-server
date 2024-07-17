package com.liam.progdleapi.snippets.entity;

import java.util.UUID;

public record CreateSnippetDto(String content, long level, UUID languageId) {
}
