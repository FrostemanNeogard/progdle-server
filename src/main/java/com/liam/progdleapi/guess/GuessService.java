package com.liam.progdleapi.guess;

import com.liam.progdleapi.guess.entity.Guess;
import com.liam.progdleapi.languages.LanguagesService;
import com.liam.progdleapi.languages.entity.Language;
import org.springframework.stereotype.Service;

@Service
public class GuessService {

    private final LanguagesService languagesService;
    private final Language CORRECT_LANGUAGE;

    private GuessService(LanguagesService languagesService) {
        this.languagesService = languagesService;
        this.CORRECT_LANGUAGE = languagesService.findLanguageByName("JavaScript");
    }

    public enum STATES {
        INCORRECT,
        PARTIAL,
        CORRECT
    }

    public Guess validateGuess(Language language) {
        Guess guess = new Guess(
                STATES.PARTIAL,
                STATES.PARTIAL,
                STATES.PARTIAL,
                STATES.PARTIAL,
                STATES.PARTIAL,
                STATES.PARTIAL
        );
        return guess;
    }

}
