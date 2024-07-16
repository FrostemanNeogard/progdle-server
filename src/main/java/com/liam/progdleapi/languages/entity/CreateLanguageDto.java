package com.liam.progdleapi.languages.entity;

public record CreateLanguageDto(
        long releaseYear,
        boolean memorySafe,
        String domain,
        String name,
        String os,
        String paradigm,
        String typing) {
}
