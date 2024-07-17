package com.liam.progdleapi.languages.entity;

import java.util.UUID;

public record LanguageDto(
        UUID id,
        long releaseYear,
        boolean memorySafe,
        String domain,
        String name,
        String os,
        String paradigm,
        String typing
) {
    public static LanguageDto from(Language language) {
        return new LanguageDto(
                language.getId(),
                language.getReleaseYear(),
                language.isMemorySafe(),
                language.getDomain(),
                language.getName(),
                language.getOs(),
                language.getParadigm(),
                language.getTyping()
        );
    }
}
