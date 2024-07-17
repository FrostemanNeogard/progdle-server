package com.liam.progdleapi.guess;

import com.liam.progdleapi.guess.entity.Guess;
import com.liam.progdleapi.languages.LanguagesService;
import com.liam.progdleapi.languages.entity.Language;
import org.springframework.stereotype.Service;

@Service
public class GuessService {

    private final LanguagesService languagesService;
    private Language CORRECT_LANGUAGE;

    private GuessService(LanguagesService languagesService) {
        this.languagesService = languagesService;
        this.CORRECT_LANGUAGE = languagesService.findLanguageByName("JavaScript");
    }

    private STATES validateReleaseYear(Language language) {
        if (language.getReleaseYear() == CORRECT_LANGUAGE.getReleaseYear()) {
            return STATES.CORRECT;
        }
        if (language.getReleaseYear() < CORRECT_LANGUAGE.getReleaseYear()) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateParadigm(Language language) {
        if (language.getParadigm().equals(CORRECT_LANGUAGE.getParadigm())) {
            return STATES.CORRECT;
        }
        if (CORRECT_LANGUAGE.getParadigm().contains(language.getParadigm())) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateTyping(Language language) {
        if (language.getTyping().equals(CORRECT_LANGUAGE.getTyping())) {
            return STATES.CORRECT;
        }
        if (CORRECT_LANGUAGE.getTyping().contains(language.getTyping())) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateDomain(Language language) {
        if (language.getDomain().equals(CORRECT_LANGUAGE.getDomain())) {
            return STATES.CORRECT;
        }
        if (CORRECT_LANGUAGE.getDomain().contains(language.getDomain())) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateName(Language language) {
        if (language.getName().equals(CORRECT_LANGUAGE.getName())) {
            return STATES.CORRECT;
        }
        return STATES.INCORRECT;
    }

    private STATES validateOs(Language language) {
        if (language.getOs().equals(CORRECT_LANGUAGE.getOs())) {
            return STATES.CORRECT;
        }
        if (CORRECT_LANGUAGE.getOs().contains(language.getOs())) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateMemorySafe(Language language) {
        if (language.isMemorySafe() == CORRECT_LANGUAGE.isMemorySafe()) {
            return STATES.CORRECT;
        }
        return STATES.INCORRECT;
    }

    public enum STATES {
        INCORRECT,
        PARTIAL,
        CORRECT
    }

    public Guess validateGuess(Language language) {
        if (CORRECT_LANGUAGE == null) {
            CORRECT_LANGUAGE = this.languagesService.findLanguageByName("JavaScript");
        }
        if (language.equals(CORRECT_LANGUAGE)) {
            return new Guess(
                    STATES.CORRECT,
                    STATES.CORRECT,
                    STATES.CORRECT,
                    STATES.CORRECT,
                    STATES.CORRECT,
                    STATES.CORRECT,
                    STATES.CORRECT
            );
        }

        return new Guess(
                validateMemorySafe(language),
                validateReleaseYear(language),
                validateDomain(language),
                validateName(language),
                validateOs(language),
                validateParadigm(language),
                validateTyping(language)
        );
    }

}
