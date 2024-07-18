package com.liam.progdleapi.guess;

import com.liam.progdleapi.guess.entity.Guess;
import com.liam.progdleapi.languages.LanguagesService;
import com.liam.progdleapi.languages.entity.Language;
import org.springframework.stereotype.Service;

@Service
public class GuessService {

    private final LanguagesService languagesService;

    private GuessService(LanguagesService languagesService) {
        this.languagesService = languagesService;
    }

    private STATES validateReleaseYear(Language language) {
        if (language.getReleaseYear() == languagesService.getDailyLanguage().getReleaseYear()) {
            return STATES.CORRECT;
        }
        if (language.getReleaseYear() < languagesService.getDailyLanguage().getReleaseYear()) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateParadigm(Language language) {
        if (language.getParadigm().equals(languagesService.getDailyLanguage().getParadigm())) {
            return STATES.CORRECT;
        }
        if (languagesService.getDailyLanguage().getParadigm().contains(language.getParadigm())) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateTyping(Language language) {
        if (language.getTyping().equals(languagesService.getDailyLanguage().getTyping())) {
            return STATES.CORRECT;
        }
        if (languagesService.getDailyLanguage().getTyping().contains(language.getTyping())) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateDomain(Language language) {
        if (language.getDomain().equals(languagesService.getDailyLanguage().getDomain())) {
            return STATES.CORRECT;
        }
        if (languagesService.getDailyLanguage().getDomain().contains(language.getDomain())) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateName(Language language) {
        if (language.getName().equals(languagesService.getDailyLanguage().getName())) {
            return STATES.CORRECT;
        }
        return STATES.INCORRECT;
    }

    private STATES validateOs(Language language) {
        if (language.getOs().equals(languagesService.getDailyLanguage().getOs())) {
            return STATES.CORRECT;
        }
        if (languagesService.getDailyLanguage().getOs().contains(language.getOs())) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateMemorySafe(Language language) {
        if (language.isMemorySafe() == languagesService.getDailyLanguage().isMemorySafe()) {
            return STATES.CORRECT;
        }
        return STATES.INCORRECT;
    }

    public long getScoreFromGuess(Guess guess) {
        long score = 0L;
        switch(guess.getName()) {
            case CORRECT -> score += 300;
            case PARTIAL -> score -= 50;
            case INCORRECT -> score -= 10;
        }
        switch(guess.getOs()) {
            case CORRECT -> score += 50;
            case PARTIAL -> score -= 5;
            case INCORRECT -> score -= 10;
        }
        switch(guess.getDomain()) {
            case CORRECT -> score += 50;
            case PARTIAL -> score -= 5;
            case INCORRECT -> score -= 10;
        }
        switch(guess.getMemorySafe()) {
            case CORRECT -> score += 25;
            case PARTIAL, INCORRECT -> score -= 10;
        }
        switch(guess.getParadigm()) {
            case CORRECT -> score += 50;
            case PARTIAL -> score -= 5;
            case INCORRECT -> score -= 10;
        }
        switch(guess.getTyping()) {
            case CORRECT -> score += 50;
            case PARTIAL -> score -= 5;
            case INCORRECT -> score -= 10;
        }
        switch(guess.getReleaseYear()) {
            case CORRECT -> score += 50;
            case PARTIAL, INCORRECT -> score -= 5;
        }
        return score;
    }

    public enum STATES {
        INCORRECT,
        PARTIAL,
        CORRECT
    }

    public Guess validateGuess(Language language) {
        return new Guess(
                validateMemorySafe(language),
                validateReleaseYear(language),
                validateDomain(language),
                validateName(language),
                validateOs(language),
                validateParadigm(language),
                validateTyping(language),
                language
        );
    }

}
