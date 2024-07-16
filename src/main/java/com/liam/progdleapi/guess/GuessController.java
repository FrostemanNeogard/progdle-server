package com.liam.progdleapi.guess;

import com.liam.progdleapi.guess.entity.Guess;
import com.liam.progdleapi.guess.entity.GuessDto;
import com.liam.progdleapi.languages.LanguagesService;
import com.liam.progdleapi.languages.entity.Language;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guess")
@CrossOrigin
public class GuessController {
    private final GuessService guessService;
    private final LanguagesService languagesService;

    public GuessController(GuessService guessService, LanguagesService languagesService) {
        this.guessService = guessService;
        this.languagesService = languagesService;
    }

    @GetMapping("/{language}")
    public ResponseEntity<GuessDto> guess(@PathVariable String language) {
        Language guessedLanguage = languagesService.findLanguageByName(language);
        if (guessedLanguage == null) {
            return ResponseEntity.notFound().build();
        }

        Guess guess = this.guessService.validateGuess(guessedLanguage);
        GuessDto dto = GuessDto.from(guess);
        return ResponseEntity.ok(dto);
    }
}
