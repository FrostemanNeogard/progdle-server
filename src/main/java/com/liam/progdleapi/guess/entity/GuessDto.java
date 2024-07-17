package com.liam.progdleapi.guess.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liam.progdleapi.guess.GuessService;
import com.liam.progdleapi.languages.entity.LanguageDto;

public record GuessDto(
        GuessService.STATES memorySafe,
        GuessService.STATES releaseYear,
        GuessService.STATES domain,
        GuessService.STATES name,
        GuessService.STATES os,
        GuessService.STATES paradigm,
        GuessService.STATES typing,
        @JsonProperty("languageData") LanguageDto language
) {
    public static GuessDto from(Guess guess) {
        return new GuessDto(
                guess.getMemorySafe(),
                guess.getReleaseYear(),
                guess.getDomain(),
                guess.getName(),
                guess.getOs(),
                guess.getParadigm(),
                guess.getTyping(),
                LanguageDto.from(guess.getLanguage())
        );
    }
}