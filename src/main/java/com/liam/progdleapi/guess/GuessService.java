package com.liam.progdleapi.guess;

import com.liam.progdleapi.guess.entity.Guess;
import com.liam.progdleapi.languages.LanguagesService;
import com.liam.progdleapi.languages.entity.Language;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
        String[] dailyParadigms = languagesService.getDailyLanguage().getParadigm().split(",");
        String[] languageParadigms = language.getParadigm().split(",");
        if (Arrays.equals(dailyParadigms, languageParadigms)) {
            return STATES.CORRECT;
        }
        if (Arrays.stream(dailyParadigms)
                .anyMatch(dp -> Arrays.asList(languageParadigms).contains(dp))) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateTyping(Language language) {
        String[] dailyTypings = languagesService.getDailyLanguage().getTyping().split(",");
        String[] languageTypings = language.getTyping().split(",");
        if (Arrays.equals(dailyTypings, languageTypings)) {
            return STATES.CORRECT;
        }
        if (Arrays.stream(dailyTypings)
                .anyMatch(dt -> Arrays.asList(languageTypings).contains(dt))) {
            return STATES.PARTIAL;
        }
        return STATES.INCORRECT;
    }

    private STATES validateDomain(Language language) {
        String[] dailyDomains = languagesService.getDailyLanguage().getDomain().split(",");
        String[] languageDomains = language.getDomain().split(",");
        if (Arrays.equals(dailyDomains, languageDomains)) {
            return STATES.CORRECT;
        }
        if (Arrays.stream(dailyDomains)
                .anyMatch(dd -> Arrays.asList(languageDomains).contains(dd))) {
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
        String[] dailyOs = languagesService.getDailyLanguage().getOs().split(",");
        String[] languageOs = language.getOs().split(",");
        if (Arrays.equals(dailyOs, languageOs)) {
            return STATES.CORRECT;
        }
        if (Arrays.stream(dailyOs)
                .anyMatch(dos -> Arrays.asList(languageOs).contains(dos))) {
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
            case PARTIAL -> score -= 5;
            case INCORRECT -> score -= 10;
        }
        switch(guess.getDomain()) {
            case PARTIAL -> score -= 5;
            case INCORRECT -> score -= 10;
        }
        switch(guess.getMemorySafe()) {
            case PARTIAL, INCORRECT -> score -= 10;
        }
        switch(guess.getParadigm()) {
            case PARTIAL -> score -= 5;
            case INCORRECT -> score -= 10;
        }
        switch(guess.getTyping()) {
            case PARTIAL -> score -= 5;
            case INCORRECT -> score -= 10;
        }
        switch(guess.getReleaseYear()) {
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
