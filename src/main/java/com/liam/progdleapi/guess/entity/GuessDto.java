package com.liam.progdleapi.guess.entity;

import com.liam.progdleapi.guess.GuessService;

public record GuessDto(
        GuessService.STATES memorySafe,
        GuessService.STATES releaseYear,
        GuessService.STATES domain,
        GuessService.STATES name,
        GuessService.STATES os,
        GuessService.STATES paradigm,
        GuessService.STATES typing) {
    public static GuessDto from(Guess guess) {
        return new GuessDto(
                guess.getMemorySafe(),
                guess.getReleaseYear(),
                guess.getDomain(),
                guess.getName(),
                guess.getOs(),
                guess.getParadigm(),
                guess.getTyping()
        );
    }
}